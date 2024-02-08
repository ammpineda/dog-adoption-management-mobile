package com.project.backend.controller;

import com.project.backend.model.admin;
import com.project.backend.service.adminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class adminController{
    @Autowired
    private adminService adminService;

    @RequestMapping(value="/add-admin", method= RequestMethod.POST)
    public admin createAdmin(@RequestBody admin admin) {
        return adminService.addAdmin(admin);
    }
    
    @RequestMapping(value="/get-admins", method=RequestMethod.GET)
    public List<admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }
    
}