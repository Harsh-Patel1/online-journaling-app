// The purpsoe of this class is to handle the authentication of the user
/// A Controller is like a traffic cop. It listens to incoming requests (e.g., /register, /login) and decides what to do 
// (e.g., call the UserService to handle logic or return a response to the user).
/// It’s part of the backend API and ensures requests are processed correctly.
/// Why Are We Doing This?
// To allow users to:
// Register: Save their information in the database.
// Log in: Authenticate themselves and ensure they’re legitimate users.

package com.example.backend.controller; // Defines the package this file belongs to

import org.springframework.beans.factory.annotation.Autowired; // Allows us to inject services
import org.springframework.http.HttpStatus; // Used for HTTP status codes
import org.springframework.http.ResponseEntity; // Used to send responses
import org.springframework.web.bind.annotation.*; // REST API annotations

import com.example.backend.entity.User; // Refers to the User entity
import com.example.backend.service.UserService; // Refers to the UserService for business logic
import jakarta.validation.Valid; // Import for validating the User object

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Handles user registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        boolean success = userService.registerUser(user);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
    }


    // Handles user login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            boolean authenticated = userService.authenticate(user.getUsername(), user.getPassword()); // Call service to authenticate
            if (authenticated) {
                return ResponseEntity.ok("Login successful");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }
}