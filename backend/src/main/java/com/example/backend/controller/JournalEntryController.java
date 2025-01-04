package com.example.backend.controller;
// Defines the package this class belongs to. Helps organize the project structure.

import com.example.backend.entity.JournalEntry;
// Imports the `JournalEntry` entity to work with journal entries.

import com.example.backend.entity.User;
// Imports the `User` entity to associate journal entries with specific users.

import com.example.backend.service.JournalEntryService;
// Imports the `JournalEntryService` to handle business logic for journal entries.

import org.springframework.beans.factory.annotation.Autowired;
// Enables dependency injection to use the `JournalEntryService`.

import org.springframework.http.HttpStatus;
// Provides HTTP status codes for API responses.

import org.springframework.http.ResponseEntity;
// Wraps responses with status codes and data for the client.

import org.springframework.web.bind.annotation.*;
// Enables REST API annotations like `@PostMapping`, `@GetMapping`, etc.

import java.util.List;
// Imports the `List` class to return a collection of journal entries.

@RestController
// Indicates that this class handles HTTP requests and sends JSON responses.

@RequestMapping("/api/journal")
// Specifies the base URL for all endpoints in this controller. All endpoints will start with `/api/journal`.

public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    // Injects the service layer to handle business logic.

    // Endpoint for creating a new journal entry
    @PostMapping("/create")
    // Maps HTTP POST requests to `/api/journal/create`.
    public ResponseEntity<JournalEntry> createEntry(
        @RequestBody JournalEntry entry,
        // Maps the JSON request body to a `JournalEntry` object.
        @RequestParam Long userId) {
        // Retrieves the user ID from the query parameters.

        User user = new User();
        // Simulates a user for now. Replace this with authenticated user logic later.
        user.setId(userId);
        // Sets the user's ID for the journal entry.

        JournalEntry createdEntry = journalEntryService.createEntry(
            entry.getTitle(), entry.getContent(), user);
        // Calls the service method to create a new journal entry.

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntry);
        // Responds with the created journal entry and a 201 CREATED status.
    }

    // Endpoint for retrieving all journal entries by a specific user
    @GetMapping("/all")
    // Maps HTTP GET requests to `/api/journal/all`.
    public ResponseEntity<List<JournalEntry>> getEntriesByUser(
        @RequestParam Long userId) {
        // Retrieves the user ID from the query parameters.

        User user = new User();
        // Simulates a user for now. Replace this with authenticated user logic later.
        user.setId(userId);
        // Sets the user's ID for the journal entries.

        List<JournalEntry> entries = journalEntryService.getEntriesByUser(user);
        // Calls the service method to retrieve all journal entries by the user.

        return ResponseEntity.ok(entries);
        // Responds with the list of journal entries and a 200 OK status.
    }

    // Endpoint for retrieving a specific journal entry by its ID
    @GetMapping("/{id}")
    // Maps HTTP GET requests to `/api/journal/{id}`, where `{id}` is the entry ID.
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable Long id) {
        // Binds the `{id}` in the URL to the `id` parameter.

        JournalEntry entry = journalEntryService.getEntryById(id);
        // Calls the service method to retrieve the journal entry by its ID.

        if (entry != null) {
            // Checks if the entry exists.
            return ResponseEntity.ok(entry);
            // Responds with the journal entry and a 200 OK status.
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        // Responds with a 404 NOT FOUND status if the entry doesn't exist.
    }

    // Endpoint for updating a journal entry
    @PutMapping("/{id}")
    // Maps HTTP PUT requests to `/api/journal/{id}`.
    public ResponseEntity<JournalEntry> updateEntry(
        @PathVariable Long id,
        // Binds the `{id}` in the URL to the `id` parameter.
        @RequestBody JournalEntry entry) {
        // Maps the JSON request body to a `JournalEntry` object.

        JournalEntry updatedEntry = journalEntryService.updateEntry(
            id, entry.getTitle(), entry.getContent());
        // Calls the service method to update the journal entry.

        return ResponseEntity.ok(updatedEntry);
        // Responds with the updated journal entry and a 200 OK status.
    }

    // Endpoint for deleting a journal entry
    @DeleteMapping("/{id}")
    // Maps HTTP DELETE requests to `/api/journal/{id}`.
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        // Binds the `{id}` in the URL to the `id` parameter.

        journalEntryService.deleteEntry(id);
        // Calls the service method to delete the journal entry by its ID.

        return ResponseEntity.noContent().build();
        // Responds with a 204 NO CONTENT status.
    }
}
