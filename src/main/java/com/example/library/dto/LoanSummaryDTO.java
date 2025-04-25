package com.example.library.dto;

import com.example.library.model.Loan;


public record LoanSummaryDTO(
        Long loanId,
        String status
) {
    public static LoanSummaryDTO fromEntity(Loan loan) {

        if (loan == null || loan.getBookCopy() == null || loan.getBookCopy().getBook() == null || loan.getBorrower() == null) {
            throw new IllegalArgumentException("Loan or related entity is null");
        }

        return new LoanSummaryDTO(
                loan.getId(),
                loan.getStatus().toString()
        );
    }
}