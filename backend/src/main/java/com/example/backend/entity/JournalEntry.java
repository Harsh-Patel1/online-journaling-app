// Package declaration: This specifies the namespace of the class.
// It helps organize code and avoid class name conflicts.
package com.example.backend.entity;
// Importing JPA (Java Persistence API) annotations for ORM mapping.
// These annotations are used to map Java objects to database tables.
import jakarta.persistence.*;
// Importing LocalDateTime to handle date and time fields in the class.
import java.time.LocalDateTime;

// The @Entity annotation tells JPA that this class is a database entity.
@Entity

// The @Table annotation maps this entity to a specific table in the database.
// The "name" attribute specifies the name of the table ("journal_entries").
@Table(name = "journal_entries")
public class JournalEntry {
    // The @Id annotation marks this field as the primary key of the table.
    // Each journal entry will have a unique ID.
    @Id

    // The @GeneratedValue annotation configures the primary key to be auto-generated.
    // The "IDENTITY" strategy tells the database to generate the ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Represents the unique identifier for a journal entry.

    // The @Column annotation is used to customize the mapping of this field to a database column.
    // The "nullable = false" attribute means this field cannot be null.
    @Column(nullable = false)
    private String title; // Represents the title of the journal entry.

    // Maps the content field to a database column with additional settings.
    // The "columnDefinition = 'TEXT'" ensures this column can hold large text.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // Represents the content of the journal entry.

    // Maps the createdAt field to a database column.
    // The "nullable = false" attribute means this field cannot be null.
    @Column(nullable = false)
    private LocalDateTime createdAt; // Represents when the journal entry was created.

    // The @ManyToOne annotation indicates a many-to-one relationship.
    // Each journal entry is associated with one user, but each user can have multiple entries.
    @ManyToOne

    // The @JoinColumn annotation specifies the foreign key column ("user_id").
    // The "nullable = false" attribute means a journal entry must always have an associated user.
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Represents the user who created the journal entry.

    // Getters and setters allow external code to access and modify the fields.
    // These are standard methods in Java for encapsulation.

    public Long getId() {
        return id; // Returns the ID of the journal entry.
    }

    public void setId(Long id) {
        this.id = id; // Sets the ID of the journal entry.
    }

    public String getTitle(){
        return title; // Returns the title of the journal entry.
    }

    public void setTitle(String title){
        this.title = title; // Sets the title of the journal entry.
    }

    public String getContent() {
        return content; // Returns the content of the journal entry.
    }

    public void setContent(String content) {
        this.content = content; // Sets the content of the journal entry.
    }

    public LocalDateTime getCreatedAt() {
        return createdAt; // Returns the creation time of the journal entry.
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; // Sets the creation time of the journal entry.
    }

    public User getUser() {
        return user; // Returns the user associated with the journal entry.
    }

    public void setUser(User user) {
        this.user = user; // Sets the user associated with the journal entry.
    }
}