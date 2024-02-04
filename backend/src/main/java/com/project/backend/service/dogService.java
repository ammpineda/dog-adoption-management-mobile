package com.project.backend.service;

import com.project.backend.model.dog;
import com.project.backend.repository.dogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class dogService{
    @Autowired
    private dogRepository dogRepository;

    // CRUD operations

    public List<dog> getAllDogs() {
        return (List<dog>) dogRepository.findAll();
    }

    public Optional<dog> getDogById(long id) {
        return dogRepository.findById((int) id);
    }

    public void addDog(dog dog) {
        dog.setRegisteredAt(LocalDateTime.now());
        dog.setModifiedAt(LocalDateTime.now());
        dogRepository.save(dog);
    }

    public void updateDog(dog dog) {
        dog.setModifiedAt(LocalDateTime.now());
        dogRepository.save(dog);
    }

    public void deleteDog(long id) {
        dogRepository.deleteById((int) id);
    }

    // Additional methods for searching

    public List<dog> findDogsByName(String name) {
        return dogRepository.findByName(name);
    }

    public List<dog> findDogsByAdoptionStatus(String adoptionStatus) {
        return dogRepository.findByAdoptionStatus(adoptionStatus);
    }
}