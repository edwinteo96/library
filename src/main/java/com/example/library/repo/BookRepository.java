package com.example.library.repo;

import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT bc FROM BookCopy bc WHERE bc.book.id = :bookId")
    List<BookCopy> findCopyByBookId(@Param("bookId") Long bookId);


}