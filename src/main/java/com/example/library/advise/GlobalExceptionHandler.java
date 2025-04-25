package com.example.library.advise;

import com.example.library.dto.ErrorResponse;
import com.example.library.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BorrowerCreationException.class)
    public ResponseEntity<ErrorResponse> handleBorrowerCreationException(BorrowerCreationException ex) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse("CREATION_FAILED", ex.getMessage()));
    }

    @ExceptionHandler(BookCreationException.class)
    public ResponseEntity<ErrorResponse> handleBookCreationException(BookCreationException ex) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse("CREATION_FAILED", ex.getMessage()));
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserIdNotFoundException(UserIdNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("RETRIEVE_FAILED", ex.getMessage()));
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("RETRIEVE_FAILED", ex.getMessage()));
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserEmailNotFoundException(UserEmailNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("RETRIEVE_FAILED", ex.getMessage()));
    }

}
