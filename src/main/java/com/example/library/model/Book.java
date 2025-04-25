package com.example.library.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookCopy> copies;

    // Constructors, getters, setters

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(Set<BookCopy> copies) {
        this.copies = copies;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Business logic methods
    public void addCopy(BookCopy copy) {
        copies.add(copy);
        copy.setBook(this);
    }

    public long getAvailableCopiesCount() {
        return copies.stream()
                .filter(c -> c.getStatus() == BookCopy.CopyStatus.AVAILABLE)
                .count();
    }
}