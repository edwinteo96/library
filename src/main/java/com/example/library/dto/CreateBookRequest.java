package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(
        @NotBlank String isbn,
        @NotBlank String author,
        @NotBlank String title
) {
}