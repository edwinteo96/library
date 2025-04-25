package com.example.library.dto;

import com.example.library.model.Loan;

import java.time.LocalDateTime;

public record ActiveLoanDTO(
        Long loanId,
        LocalDateTime loanDate,
        String status,
        LocalDateTime returnDate
) {
    public static ActiveLoanDTO fromEntity(Loan loan) {
        return new ActiveLoanDTO(
                loan.getId(),
                loan.getLoanDate(),
                loan.getStatus().toString(),
                loan.getReturnDate()
        );
    }
}