package com.example.library.service;

import com.example.library.dto.CreateBookRequest;
import com.example.library.exception.BookCreationException;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.repo.BookCopyRepository;
import com.example.library.repo.BookRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    private final BookCopyRepository bookCopyRepository;

    public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<Book> retrieveBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(CreateBookRequest request) {

        return bookRepository.findByIsbn(request.isbn()).map(
                b -> {
                    if ( !(b.getAuthor().equals(request.author()) && b.getTitle().equals(request.title())))
                        throw new BookCreationException(request.isbn());

                    List<BookCopy> copyList = bookRepository.findCopyByBookId(b.getId());
                    int copyCount = copyList.size();

                    BookCopy bc = new BookCopy();
                    bc.setBook(b);
                    bc.setStatus(BookCopy.CopyStatus.AVAILABLE);
                    bc.setCopyNumber(++copyCount);
                    try {
                        bookCopyRepository.save(bc);
                        return b;
                    } catch (JpaSystemException | PersistenceException e) {
                        throw new BookCreationException(request.isbn());
                    }
                }).orElseGet(() -> {
            try {
                Book b = new Book();
                b.setIsbn(request.isbn());
                b.setTitle(request.title());
                b.setAuthor(request.author());
                bookRepository.save(b);

                BookCopy bc = new BookCopy();
                bc.setBook(b);
                bc.setCopyNumber(1);
                bookCopyRepository.save(bc);
                return b;
            } catch (JpaSystemException | PersistenceException e) {
                throw new BookCreationException(request.isbn());
            }
        });

    }


}