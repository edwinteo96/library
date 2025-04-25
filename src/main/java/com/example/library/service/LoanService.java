package com.example.library.service;

import com.example.library.dto.CreateLoanRequest;
import com.example.library.dto.ReturnLoanRequest;
import com.example.library.exception.BookCopyNotFoundException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.LoanNotFoundException;
import com.example.library.exception.UserEmailNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.model.Borrower;
import com.example.library.model.Loan;
import com.example.library.repo.BookRepository;
import com.example.library.repo.BorrowerRepository;
import com.example.library.repo.LoanRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@Tag(name = "Loan Management", description = "Endpoints for managing loans")
public class LoanService {

    private final LoanRepository loanRepository;

    private final BookRepository bookRepository;

    private final BorrowerRepository borrowerRepository;

    public LoanService(@Autowired LoanRepository loanRepository, @Autowired BorrowerRepository borrowerRepository, @Autowired BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    public Loan createLoan(CreateLoanRequest request) {
        Book b = bookRepository.findByIsbn(request.bookIsbn())
                .orElseThrow(() -> new BookNotFoundException(request.bookIsbn()));

        Borrower br = borrowerRepository.findByEmail(request.borrowerEmail())
                .orElseThrow(() -> new UserEmailNotFoundException(request.borrowerEmail()));

        BookCopy availableCopy = b.getCopies().stream()
                .filter(copy -> copy.getStatus() == BookCopy.CopyStatus.AVAILABLE)
                .findFirst().orElseThrow(() -> new BookCopyNotFoundException(b.getId()));

        availableCopy.setStatus(BookCopy.CopyStatus.BORROWED);

        Loan l = new Loan();
        l.setBorrower(br);
        l.setBookCopy(availableCopy);
        l.setDueDate(LocalDateTime.now().plusDays(7));

        return loanRepository.save(l);
    }

    public Loan processReturn(ReturnLoanRequest request) {
        Loan loan = loanRepository.findById(request.loanId()).orElseThrow(()-> new LoanNotFoundException(request.loanId()));
        loan.returnBook();
        loanRepository.save(loan);
        return loan;
    }


}