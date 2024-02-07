package com.project.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="dog")
public class dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String displayImagePath;
    private String breed;
    private String birthDate;
    private String gender;
    private String weight; // in kg
    private String adoptionStatus = "Available"; // default is "Available"
    @Type(type="text")
    private String description; // traits
    private LocalDateTime registeredAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "dog")
    @JsonIgnore
    private List<application> applications;

    public dog() {
    }

    public dog(int id, String name, String displayImagePath, String breed, String birthDate, String gender,
            String weight, String adoptionStatus, String description, LocalDateTime registeredAt,
            LocalDateTime modifiedAt, List<application> applications) {
        this.id = id;
        this.name = name;
        this.displayImagePath = displayImagePath;
        this.breed = breed;
        this.birthDate = birthDate;
        this.gender = gender;
        this.weight = weight;
        this.adoptionStatus = adoptionStatus;
        this.description = description;
        this.registeredAt = registeredAt;
        this.modifiedAt = modifiedAt;
        this.applications = applications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayImagePath() {
        return displayImagePath;
    }

    public void setDisplayImagePath(String displayImagePath) {
        this.displayImagePath = displayImagePath;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public List<application> getApplications() {
        return applications;
    }

    public void setApplications(List<application> applications) {
        this.applications = applications;
    }


}
