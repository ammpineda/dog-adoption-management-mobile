package com.project.backend.service;

import com.project.backend.model.application;
import com.project.backend.repository.applicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class applicationService {

    @Autowired
    private applicationRepository applicationRepository;

    // CRUD operations

    public List<application> getAllApplications() {
        return (List<application>) applicationRepository.findAll();
    }

    public Optional<application> getApplicationById(long id) {
        return applicationRepository.findById((int) id);
    }

    public application addApplication(application application) {
        application.setStatus("Submitted");
        String randomString = generateRandomString(6);
        application.setReferenceCode(randomString);
        application.setSubmittedAt(LocalDateTime.now());
        application.setModifiedAt(LocalDateTime.now());
        return applicationRepository.save(application);
    }

    public void updateApplication(application application) {
        application.setModifiedAt(LocalDateTime.now());
        applicationRepository.save(application);
    }

    public void deleteApplication(long id) {
        applicationRepository.deleteById((int) id);
    }

    // Additional methods for searching

    public List<application> findApplicationsByReferenceCode(String referenceCode) {
        return applicationRepository.findByReferenceCode(referenceCode);
    }

    public List<application> findApplicationsByDogName(String dogName) {
        return applicationRepository.findByDogName(dogName);
    }

    public List<application> findApplicationsByApplicantId(long id) {
        return applicationRepository.findByApplicantId(id);
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    // Method to generate a random alphanumeric string of a specified length
    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
