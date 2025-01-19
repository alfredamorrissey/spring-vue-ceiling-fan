package com.example.springvue.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")  // Allow requests from Vue.js frontend
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}