package com.example.library.dto;


import com.example.library.model.Borrower;
import com.example.library.model.Loan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record BorrowerDTO(
        Long id,
        String email,
        String name,
        LocalDateTime createdAt,
        List<ActiveLoanDTO> activeLoans
) {

    public BorrowerDTO {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
    }

    public static BorrowerDTO fromEntity(Borrower borrower) {
        return new BorrowerDTO(
                borrower.getId(),
                borrower.getEmail(),
                borrower.getName(),
                borrower.getCreatedAt(),
                borrower.getLoans().stream().map(ActiveLoanDTO::fromEntity).collect(Collectors.toList())
        );
    }
}