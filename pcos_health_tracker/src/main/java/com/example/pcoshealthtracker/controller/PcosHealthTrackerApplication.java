
package com.example.pcoshealthtracker.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PcosHealthTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcosHealthTrackerApplication.class, args);
    }
}
