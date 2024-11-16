package com.example.pcoshealthtracker.controller.repository;

import com.example.pcoshealthtracker.controller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email); // Check if email exists
    boolean existsByUsername(String username); // Optional: Check if username exists
}
