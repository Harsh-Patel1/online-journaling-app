package com.example.backend.entity; // This means the class belongs to the com.example.backend.entity package. Came from the spring initalizer step i did at the start which genereated, the group name is com.example and the aritfact name is backend, all of which i created myself

// imports everything (*) from the jakarta.persistence package, 
// which contains important JPA (Java Persistence API) annotations like @Entity, @Table, and @Id
import jakarta.persistence.*; 

// The @Entity annotation tells Hibernate (or any ORM tool) that the class is a database entity.
// Hibernate automatically maps the class to a corresponding table in the database.
@Entity

@Table(name = "users") // The @Table  annotation explicitly specifies the database table name as "users".

// The User class is a POJO (Plain Old Java Object) that represents a user entity in the database.
public class User {

    @Id // The @Id annotation specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The @GeneratedValue annotation provides the generation strategy for the primary key.
    private Long id; // The id field is the primary key of the users table.

    @Column(unique = true, nullable = false)
    // @Column: Specifies column properties.
    // unique = true: Ensures the "username" column values are unique.
    // nullable = false: Makes the column mandatory (it cannot be null).
    private String username;

    @Column(nullable = false)
    @Size(min = 6, max = 25, message = "Password must be at least 6 characters long")
    // This column stores the user's hashed password.
    // It is mandatory (nullable = false).
    private String password;

    @Column(nullable = false)
    @Email(message = "Email should be valid")
    // This column stores the user's email address.
    // It is mandatory (nullable = false).
    // `@Email` ensures the email follows a valid email format.
    private String email;

    // Getters and setters, used so other parts of the application can access and modify them.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}