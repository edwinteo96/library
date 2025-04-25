package com.example.library.controller;

import com.example.library.dto.*;
import com.example.library.model.Loan;
import com.example.library.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(@Autowired LoanService loanService) {
        this.loanService = loanService;
    }

    // Create new loan
    @PostMapping("/create")
    @Operation(summary = "Create Loan using book id , borrower id as reference key",
            description = "Create Loan and Return the Loan Information")
    @ApiResponse(responseCode = "201", description = "Loan created")
    @ApiResponse(responseCode = "500", description = "Loan failed to created")
    public ResponseEntity<?> createLoan(
            @Valid @RequestBody CreateLoanRequest request) {
        Loan loan = loanService.createLoan(request);
        return ResponseEntity.created(URI.create("/loans/" + loan.getId()))
                .body(LoanDTO.fromEntity(loan));
    }

    // Return a book based on id
    @PostMapping("/return")
    @Operation(summary = "Return A Borrowed Book",
            description = "Return A Book Based on Loan")
    @ApiResponse(responseCode = "200", description = "Return book successfully")
    public ResponseEntity<LoanSummaryDTO> returnLoan(
            @Valid @RequestBody ReturnLoanRequest request) {
        Loan returnedLoan = loanService.processReturn(request);
        return ResponseEntity.ok(LoanSummaryDTO.fromEntity(returnedLoan));
    }

}