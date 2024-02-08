package com.project.backend.controller;

import com.project.backend.model.admin;
import com.project.backend.model.adopter;
import com.project.backend.service.adminService;
import com.project.backend.service.adopterService;
import com.project.backend.service.dogService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/")
public class adopterController{
    @Autowired
    private adopterService adopterService;


    @GetMapping("all-adopters")
    public List<adopter> getAllAdopters() {
        return adopterService.getAllAdopters();
    }
    

    @PostMapping("add-adopter")
    public adopter createAdopter(@RequestBody adopter adopter) {
        return adopterService.addAdopter(adopter);
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

}