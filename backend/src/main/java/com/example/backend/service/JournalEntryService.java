package com.example.backend.service;
// Specifies that this class belongs to the 'service' package, which is part of your backend application.

/*
This service class acts as the middle layer between the controller (handling HTTP requests) 
and the repository (interacting with the database). 
It encapsulates the business logic, ensuring a clean separation of concerns in your application.
*/

import com.example.backend.entity.JournalEntry;
// Imports the `JournalEntry` entity to use it for creating, updating, and retrieving entries.

import com.example.backend.entity.User;
// Imports the `User` entity, likely for associating journal entries with specific users.

import com.example.backend.repository.JournalEntryRepository;
// Imports the repository interface to interact with the database for CRUD operations on `JournalEntry`.

import org.springframework.beans.factory.annotation.Autowired;
// Allows Spring to automatically inject required dependencies, such as the `JournalEntryRepository`.

import org.springframework.stereotype.Service;
// Marks this class as a service in Spring. It tells Spring to manage it as part of the application's business logic layer.

import java.time.LocalDateTime;
// Provides the `LocalDateTime` class for handling date and time, used for timestamping journal entries.

import java.util.List;
// Imports `List`, a collection interface to return multiple `JournalEntry` objects.

@Service
// Indicates that this class contains the business logic and is a service component in the application.
public class JournalEntryService {
    // This class handles the business logic for journal entries, separating it from the controller and repository layers.

    @Autowired
    // Automatically injects the `JournalEntryRepository` dependency into this class.
    private JournalEntryRepository journalEntryRepository;

    // Method to create a new journal entry.
    public JournalEntry createEntry(String title, String content, User user) {
        // Takes a title, content, and user as input to create a new journal entry.
        
        JournalEntry entry = new JournalEntry();
        // Creates a new instance of the `JournalEntry` entity.
        
        entry.setTitle(title);
        // Sets the title of the journal entry.
        
        entry.setContent(content);
        // Sets the content of the journal entry.
        
        entry.setCreatedAt(LocalDateTime.now());
        // Sets the current date and time as the entry's creation time.
        
        entry.setUser(user);
        // Associates the journal entry with a specific user.
        
        return journalEntryRepository.save(entry);
        // Saves the journal entry in the database and returns the saved entry.
    }

    // Method to retrieve all journal entries by a specific user.
    public List<JournalEntry> getEntriesByUser(User user) {
        // Retrieves all journal entries. Currently, it fetches all entries, not filtered by user.
        
        return journalEntryRepository.findAll();
        // Fetches all entries from the database. This can be updated to include a custom query for filtering by user.
    }

    // Method to retrieve a specific journal entry by its ID.
    public JournalEntry getEntryById(Long id) {
        // Takes an ID as input and retrieves the corresponding journal entry.
        
        return journalEntryRepository.findById(id).orElse(null);
        // Returns the entry if found; otherwise, returns null.
    }

    // Method to update an existing journal entry.
    public JournalEntry updateEntry(Long id, String title, String content) {
        // Takes an ID, title, and content as input to update a journal entry.
        
        JournalEntry entry = journalEntryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entry not found"));
        // Fetches the journal entry by ID or throws an exception if it doesn't exist.
        
        entry.setTitle(title);
        // Updates the title of the entry.
        
        entry.setContent(content);
        // Updates the content of the entry.
        
        return journalEntryRepository.save(entry);
        // Saves the updated entry in the database and returns it.
    }

    // Method to delete a journal entry by its ID.
    public void deleteEntry(Long id) {
        // Takes an ID as input to delete a journal entry.
        
        journalEntryRepository.deleteById(id);
        // Deletes the journal entry with the specified ID from the database.
    }
}
