package com.example.library.exception;

public class InvalidLoanRequestException extends IllegalArgumentException {

    public InvalidLoanRequestException(String message) {
        super(message);
    }

}