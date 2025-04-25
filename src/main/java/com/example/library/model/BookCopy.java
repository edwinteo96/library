package com.example.library.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "copy_number", nullable = false)
    private Integer copyNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CopyStatus status = CopyStatus.AVAILABLE;

    @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL)
    private Set<Loan> loans = new HashSet<>();

    // Enums
    public enum CopyStatus {
        AVAILABLE, BORROWED, LOST, MAINTENANCE
    }

    // Getters, setters, equals, hashCode

    public void setStatus(CopyStatus status) {
        this.status = status;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public Integer getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(Integer copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public boolean isAvailable() {
        return status == CopyStatus.AVAILABLE;
    }

}