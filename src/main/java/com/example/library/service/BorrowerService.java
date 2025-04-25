package com.example.library.service;

import com.example.library.dto.CreateBorrowerRequest;
import com.example.library.exception.BorrowerCreationException;
import com.example.library.exception.UserEmailNotFoundException;
import com.example.library.exception.UserIdNotFoundException;
import com.example.library.model.Borrower;
import com.example.library.repo.BorrowerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Transactional
@Tag(name = "Borrower Management", description = "Endpoints for managing borrower")
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Borrower createBorrower(CreateBorrowerRequest request) throws BorrowerCreationException {
        try {
            return borrowerRepository.save(new Borrower(request.email(), request.name()));
        } catch (DataIntegrityViolationException e) {
            throw new BorrowerCreationException("Email already exists", e);
        } catch (JpaSystemException | PersistenceException e) {
            throw new BorrowerCreationException("Failed to create borrower", e);
        }
    }

    public Borrower retrieveBorrower(Long id) {
        return borrowerRepository.findByIdWithLoans(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }


}