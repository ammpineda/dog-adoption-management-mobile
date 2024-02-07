package com.project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.backend.model.adopter;
import com.project.backend.model.application;
import com.project.backend.model.dog;


@RestController
@RequestMapping("/")
public class applicationController{
    @Autowired
    private com.project.backend.service.applicationService applicationService;

    @PostMapping("add-application")
    public application createApplication(@RequestBody application application) {
        return applicationService.addApplication(application);
    }

    @GetMapping("all-applications")
    public List<application> getAllApplications() {
        return applicationService.getAllApplications();
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

}