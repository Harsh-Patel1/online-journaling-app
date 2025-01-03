package com.example.backend.exception;

// Custom exception for handling cases where a user already exists
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}