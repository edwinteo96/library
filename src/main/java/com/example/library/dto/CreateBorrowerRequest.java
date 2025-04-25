package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;


public record CreateBorrowerRequest(
        @NotBlank String name,
        @NotBlank String email
) {
}