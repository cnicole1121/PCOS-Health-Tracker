package com.example.pcoshealthtracker.controller;

import com.example.pcoshealthtracker.controller.entity.User;
import com.example.pcoshealthtracker.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api") // Base path for all endpoints in this controller
public class RegistrationController {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        // Check if email or username already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            response.put("status", "error");
            response.put("message", "Email is already registered.");
            return ResponseEntity.badRequest().body(response);
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            response.put("status", "error");
            response.put("message", "Username is already taken.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            userRepository.save(user);
            response.put("status", "success");
            response.put("redirectUrl", "/Login.html");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/check-availability")
    public ResponseEntity<Map<String, Boolean>> checkAvailability(@RequestBody Map<String, String> payload) {
        Map<String, Boolean> response = new HashMap<>();
        
        String email = payload.get("email");
        String username = payload.get("username");

        if (email != null && !email.isEmpty()) {
            response.put("available", !userRepository.existsByEmail(email));
            return ResponseEntity.ok(response);
        }

        if (username != null && !username.isEmpty()) {
            response.put("available", !userRepository.existsByUsername(username));
            return ResponseEntity.ok(response);
        }

        response.put("available", false); // Invalid request
        return ResponseEntity.badRequest().body(response);
    }

}