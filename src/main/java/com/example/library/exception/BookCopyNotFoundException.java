package com.example.library.exception;

import jakarta.validation.constraints.NotBlank;

public class BookCopyNotFoundException extends RuntimeException {
    public BookCopyNotFoundException(@NotBlank Long s) {
        super("Book Copy not found for book : " + s);
    }
}
