package com.example.library.dto;

import com.example.library.model.Book;

public record BookDTO(Long id, String isbn, String title , String author) {

    public static BookDTO fromEntity(Book book) {
        return new BookDTO(book.getId(), book.getIsbn(), book.getTitle() , book.getAuthor());
    }
}