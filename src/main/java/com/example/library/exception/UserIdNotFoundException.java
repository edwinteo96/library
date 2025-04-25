package com.example.library.exception;

import jakarta.validation.constraints.NotBlank;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(@NotBlank Long s) {
        super("Borrower not found with id: " + s);
    }
}
