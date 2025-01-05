package com.example.backend.controller;

import com.example.backend.dto.JournalEntryResponse; // Import the DTO
import com.example.backend.entity.JournalEntry;
import com.example.backend.entity.User;
import com.example.backend.service.JournalEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors; // To convert a list of entities to DTOs

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public ResponseEntity<JournalEntryResponse> createEntry(
        @RequestBody JournalEntry entry,
        @RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        JournalEntry createdEntry = journalEntryService.createEntry(
            entry.getTitle(), entry.getContent(), user);

        // Convert the entity to a DTO
        JournalEntryResponse response = new JournalEntryResponse(createdEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JournalEntryResponse>> getEntriesByUser(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        List<JournalEntry> entries = journalEntryService.getEntriesByUser(user);

        // Convert the list of entities to a list of DTOs
        List<JournalEntryResponse> responses = entries.stream()
            .map(JournalEntryResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntryResponse> getEntryById(@PathVariable Long id) {
        JournalEntry entry = journalEntryService.getEntryById(id);
        if (entry != null) {
            // Convert the entity to a DTO
            return ResponseEntity.ok(new JournalEntryResponse(entry));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntryResponse> updateEntry(
        @PathVariable Long id,
        @RequestBody JournalEntry entry) {
        JournalEntry updatedEntry = journalEntryService.updateEntry(
            id, entry.getTitle(), entry.getContent());

        // Convert the updated entity to a DTO
        return ResponseEntity.ok(new JournalEntryResponse(updatedEntry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        journalEntryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}