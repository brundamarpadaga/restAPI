package com.prodapt.bicyclespring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/some-resource")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminResource() {
        // Implement your admin resource logic here
        return ResponseEntity.ok("Admin resource accessed!");
    }
}
