package com.example.backend.dto;

import com.example.backend.entity.JournalEntry;
import com.example.backend.entity.User;
import java.time.LocalDateTime;

public class JournalEntryResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private UserResponse user;

    public JournalEntryResponse(JournalEntry journalEntry) {
        this.id = journalEntry.getId();
        this.title = journalEntry.getTitle();
        this.content = journalEntry.getContent();
        this.createdAt = journalEntry.getCreatedAt();
        this.user = new UserResponse(journalEntry.getUser());
    }

    // Getters and Setters for JournalEntryResponse
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public static class UserResponse {
        private Long id; // Only include the user ID, not the username.

        public UserResponse(User user) {
            this.id = user.getId();
        }

        // Getters and Setters for UserResponse
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}