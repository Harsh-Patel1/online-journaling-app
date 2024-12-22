import com.example.backend.entity.User;
// We need the `User` entity to interact with the database.

import com.example.backend.repository.UserRepository;
// The `UserRepository` allows us to perform CRUD operations.

import org.springframework.beans.factory.annotation.Autowired;
// Spring's `@Autowired` injects dependencies like the repository and password encoder.

import org.springframework.security.crypto.password.PasswordEncoder;
// The `PasswordEncoder` hashes user passwords for security.

import org.springframework.stereotype.Service;
// The `@Service` annotation marks this class as a service in Spring.

@Service

public class UserService {
    // The `@Autowired` annotation injects the `UserRepository` dependency.
    @Autowired
    private UserRepository userRepository;

    // The `@Autowired` annotation injects the `PasswordEncoder` dependency.
    @Autowired
    private PasswordEncoder passwordEncoder;

    // The `saveUser` method saves a new user to the database.
    public User saveUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        return userRepository.save(user);
    }
}