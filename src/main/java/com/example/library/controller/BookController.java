package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.dto.CreateBookRequest;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "Endpoints for managing books")
public class BookController {

    private final BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    // Create new loan
    @PostMapping("/create")
    @Operation(summary = "Create Book using isbn , title , author",
            description = "Create and Return the Book Information")
    @ApiResponse(responseCode = "201", description = "Book created")
    @ApiResponse(responseCode = "500", description = "Book failed to created")
    public ResponseEntity<?> createBooks(
            @Valid @RequestBody CreateBookRequest request) {
        Book book = bookService.createBook(request);

        return ResponseEntity.created(URI.create("/api/books/" + book.getId()))
                .body(BookDTO.fromEntity(book));
    }

    // Return all books
    @GetMapping
    @Operation(summary = "Retrieve All Books",
            description = "Retrieve All Books Information")
    @ApiResponse(responseCode = "200", description = "Return list of books")
    public ResponseEntity<List<BookDTO>> retrieveBooks() {
        List<BookDTO> books = bookService.retrieveBooks().stream().map(BookDTO::fromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

}