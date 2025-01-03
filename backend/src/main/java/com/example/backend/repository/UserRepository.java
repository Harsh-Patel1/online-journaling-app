package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// The UserRepository interface extends the JpaRepository interface.
// The JpaRepository interface provides methods for performing CRUD operations on the User entity such as save, findById, delete, etc
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find a user by username
    Optional<User> findByUsername(String username);

}