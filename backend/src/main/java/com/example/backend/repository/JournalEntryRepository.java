package com.example.backend.repository;
/*
Purpose: This repository will allow us to perform database operations 
(CRUD) for journal entries.
*/
// Import necessary Spring Data JPA classes for repository creation.
import com.example.backend.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// The @Repository annotation indicates this interface is a Spring-managed repository.
@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

    // Custom query methods can be added here if needed.
    // For example, find all entries by a specific user:
    // List<JournalEntry> findByUserId(Long userId);
}