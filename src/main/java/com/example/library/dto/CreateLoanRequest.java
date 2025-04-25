package com.example.library.dto;


import jakarta.validation.constraints.NotBlank;


public record CreateLoanRequest(
        @NotBlank String bookIsbn,
        @NotBlank String borrowerEmail
) {
}