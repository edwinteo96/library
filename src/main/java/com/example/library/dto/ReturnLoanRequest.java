package com.example.library.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReturnLoanRequest(
        @NotNull(message = "Loan ID cannot be null")
        @Min(value = 1, message = "Loan ID must be at least 1")
        Long loanId
) {
}