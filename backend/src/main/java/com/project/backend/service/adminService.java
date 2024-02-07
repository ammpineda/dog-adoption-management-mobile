package com.project.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.model.admin;
import com.project.backend.repository.adminRepository;

@Service
public class adminService {
    @Autowired
    private adminRepository adminRepository;

    public List<admin> getAllAdmins() {
        return (List<admin>) adminRepository.findAll();
    }

    public Optional<admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public admin addAdmin(admin admin) {
        return adminRepository.save(admin);
    }

    public void updateAdmin(admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    public boolean login(String email, String password) {
        admin admin = adminRepository.findAdminByEmail(email);

        return admin != null && admin.getPassword().equals(password);
    }

    
}
