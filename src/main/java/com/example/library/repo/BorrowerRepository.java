package com.example.library.repo;

import com.example.library.model.BookCopy;
import com.example.library.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

    @Query("SELECT b FROM Borrower b WHERE b.email = :email")
    Optional<Borrower> findByEmail(@Param("email") String email);

    @Query("SELECT b FROM Borrower b LEFT JOIN FETCH b.loans l LEFT JOIN FETCH l.bookCopy bc WHERE b.id = :id")
    Optional<Borrower> findByIdWithLoans(@Param("id") Long id);


}