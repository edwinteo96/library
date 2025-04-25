package com.example.library.exception;

import jakarta.validation.constraints.NotBlank;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(@NotBlank String s) {
        super("Book not found with ISBN: " + s);
    }

}
