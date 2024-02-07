package com.project.backend.controller;

import com.project.backend.model.admin;
import com.project.backend.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class adminController{
    @Autowired
    private adminService adminService;

    @RequestMapping(value="/add-admin", method= RequestMethod.POST)
    public admin createAdmin(@RequestBody admin admin) {
        return adminService.addAdmin(admin);
    }

    @RequestMapping(value="/login-admin", method= RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        if (adminService.login(email, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}