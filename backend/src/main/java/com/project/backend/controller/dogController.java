package com.project.backend.controller;

import com.project.backend.model.dog;
import com.project.backend.service.adminService;
import com.project.backend.service.dogService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class dogController{
    @Autowired
    private dogService dogService;

    @PostMapping("create")
    public dog addDog(@RequestBody dog dog) {
        return dogService.addDog(dog);
    }

    @GetMapping("all-dogs")
    public List<dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("dog/{id}")
    public dog getDogById(@PathVariable long id) {
        return dogService.getDogById(id).orElse(null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

}