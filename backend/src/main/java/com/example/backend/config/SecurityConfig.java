package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in development
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // Public API endpoints
                .requestMatchers("/error").permitAll()  // Allow error handling endpoints
                .anyRequest().authenticated()          // Secure all other endpoints
            )
            .httpBasic(); // Use Basic Authentication for simplicity
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Optionally specify a strength parameter (e.g., 12) for BCrypt
        return new BCryptPasswordEncoder();
    }
}