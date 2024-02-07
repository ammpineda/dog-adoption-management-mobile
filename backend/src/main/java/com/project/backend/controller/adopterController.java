package com.project.backend.controller;

import com.project.backend.model.admin;
import com.project.backend.service.adminService;
import com.project.backend.service.adopterService;
import com.project.backend.service.dogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adopter")
public class adopterController{
    @Autowired
    private adopterService adopterService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

}