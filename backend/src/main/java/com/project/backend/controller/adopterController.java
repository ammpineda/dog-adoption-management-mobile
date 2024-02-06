package com.project.backend.controller;

import ch.qos.logback.classic.Logger;
import com.project.backend.model.adopter;
import com.project.backend.service.adopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/adopters")
public class adopterController {

    @Autowired
    private adopterService adopterService;

    @GetMapping("/all-adopter")
    public ResponseEntity<List<adopter>> getAllAdopters() {
        List<adopter> adopters = adopterService.getAllAdopters();
        return new ResponseEntity<>(adopters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<adopter> getAdopterById(@PathVariable("id") long id) {
        Optional<adopter> adopter = adopterService.getAdopterById(id);
        return adopter.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Void> addAdopter(@RequestBody adopter adopter) {
        adopterService.addAdopter(adopter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdopter(@PathVariable("id") long id, @RequestBody adopter adopter) {
        adopter.setId(id);
        adopterService.updateAdopter(adopter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdopter(@PathVariable("id") long id) {
        adopterService.deleteAdopter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<adopter>> findAdoptersByName(@PathVariable("name") String name) {
        List<adopter> adopters = adopterService.findAdoptersByName(name);
        return new ResponseEntity<>(adopters, HttpStatus.OK);
    }
}
  
