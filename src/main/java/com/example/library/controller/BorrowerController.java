package com.example.library.controller;

import com.example.library.dto.*;
import com.example.library.exception.BorrowerCreationException;
import com.example.library.model.Borrower;
import com.example.library.service.BorrowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrower")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(@Autowired BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    // Create borrower
    @PostMapping("/create")
    @Operation(summary = "Create Borrower using email , name",
            description = "Create and Return the Borrower Information")
    @ApiResponse(responseCode = "201", description = "Borrower created")
    @ApiResponse(responseCode = "500", description = "Borrower failed to created")
    public ResponseEntity<BorrowerDTO> createBorrower(
            @Valid @RequestBody CreateBorrowerRequest request) throws BorrowerCreationException {
        Borrower borrower = borrowerService.createBorrower(request);
        return ResponseEntity.created(URI.create("/api/borrower/" + borrower.getId()))
                .body(BorrowerDTO.fromEntity(borrower));
    }

    // Return a borrower based on id
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve Borrower",
            description = "Retrieve Borrower Information Based on Id")
    @ApiResponse(responseCode = "200", description = "Return list of borrower")
    @ApiResponse(responseCode = "404", description = "Borrower not found")
    public ResponseEntity<BorrowerDTO> retrieveBorrower(
            @PathVariable Long id) {
        Borrower borrower = borrowerService.retrieveBorrower(id);
        return ResponseEntity.ok(BorrowerDTO.fromEntity(borrower));
    }

}