package com.example.springvue.controller;

import com.example.springvue.model.Fan;
import com.example.springvue.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")  // Allow requests from Vue.js frontend
@RequestMapping("/api/fans")
public class FanController {

    @Autowired
    private FanService fanService;

    @GetMapping
    public ResponseEntity<List<Fan>> getAllFans() {
        List<Fan> fans = fanService.getAllFans();
        return ResponseEntity.ok(fans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fan> getFanById(@PathVariable Long id) {
        Fan fan = fanService.getFanById(id);
        return fan != null ? ResponseEntity.ok(fan) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Fan> createFan(@RequestBody Fan fan) {
        Fan createdFan = fanService.createFan(fan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fan> updateFan(@PathVariable Long id, @RequestBody Fan fan) {
        Fan updatedFan = fanService.updateFan(id, fan);
        return updatedFan != null ? ResponseEntity.ok(updatedFan) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFan(@PathVariable Long id) {
        boolean deleted = fanService.deleteFan(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllFans() {
        fanService.deleteAllFans();
    }
}
