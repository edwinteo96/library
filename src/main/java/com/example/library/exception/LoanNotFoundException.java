package com.example.library.exception;

import jakarta.validation.constraints.NotBlank;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(@NotBlank Long s) {
        super("Loan record not found with Id: " + s);
    }

}
