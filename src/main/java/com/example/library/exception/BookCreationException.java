package com.example.library.exception;

import jakarta.validation.constraints.NotBlank;

public class BookCreationException extends RuntimeException {
    public BookCreationException(@NotBlank String s) {
        super("Book Creation Failed For ISBN : " + s);
    }

}
