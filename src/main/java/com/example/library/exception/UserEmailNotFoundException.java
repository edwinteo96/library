package com.example.library.exception;

import jakarta.validation.constraints.NotBlank;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException(@NotBlank String s) {
        super("Borrower not found with email: " + s);
    }
}
