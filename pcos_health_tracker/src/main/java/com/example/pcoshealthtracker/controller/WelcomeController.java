package com.example.pcoshealthtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/api/welcome")
    public String welcomeMessage() {
        return "Welcome to the PCOS Health Tracker!";
    }
}
