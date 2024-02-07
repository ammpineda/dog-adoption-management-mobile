package com.project.backend.service;

import com.project.backend.model.adopter;
import com.project.backend.repository.adopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class adopterService {

    @Autowired
    private adopterRepository adopterRepository;

    // CRUD operations

    public List<adopter> getAllAdopters() {
        return (List<adopter>) adopterRepository.findAll();
    }

    public Optional<adopter> getAdopterById(long id) {
        return adopterRepository.findById((int) id);
    }

    public adopter addAdopter(adopter adopter) {
        adopter.setRegisteredAt(LocalDateTime.now());
        adopter.setUpdatedAt(LocalDateTime.now());
        return adopterRepository.save(adopter);
    }

    public void updateAdopter(adopter adopter) {
        adopter.setUpdatedAt(LocalDateTime.now());
        adopterRepository.save(adopter);
    }

    public void deleteAdopter(long id) {
        adopterRepository.deleteById((int) id);
    }

    // Additional methods for searching

    public List<adopter> findAdoptersByEmail(String email) {
        return adopterRepository.findByEmail(email);
    }

    public List<adopter> findAdoptersByContactNumber(String contactNumber) {
        return adopterRepository.findByContactNumber(contactNumber);
    }

    public Integer getHighestAdopterId() {
        Integer highestId = adopterRepository.findHighestId();
        return highestId != null ? highestId : 0;
    }

    

}