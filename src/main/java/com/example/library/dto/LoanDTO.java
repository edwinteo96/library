package com.example.library.dto;

import com.example.library.model.Loan;

import java.time.LocalDateTime;

public record LoanDTO(
        Long loanId,
        String bookTitle,
        String bookIsbn,
        Integer copyNumber,
        String borrowerName,
        LocalDateTime loanDate,
        LocalDateTime dueDate,
        String status,
        LocalDateTime returnDate
) {
    public static LoanDTO fromEntity(Loan loan) {

        if (loan == null || loan.getBookCopy() == null || loan.getBookCopy().getBook() == null || loan.getBorrower() == null) {
            throw new IllegalArgumentException("Loan or related entity is null");
        }

        return new LoanDTO(
                loan.getId(),
                loan.getBookCopy().getBook().getTitle(),
                loan.getBookCopy().getBook().getIsbn(),
                loan.getBookCopy().getCopyNumber(),
                loan.getBorrower().getName(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getStatus().toString(),
                loan.getReturnDate()
        );
    }
}