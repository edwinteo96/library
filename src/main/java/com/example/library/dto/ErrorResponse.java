package com.example.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        // Optional for validation errors
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<String> details
) {
    public ErrorResponse(String code, String message) {
        this(code, message, Collections.emptyList());
    }
}