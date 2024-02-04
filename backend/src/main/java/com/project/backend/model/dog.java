package com.project.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="dog")
public class dog {
    @Id
    private long id;
    private String name;
    private String displayImagePath;
    private String breed;
    private Date birthDate;
    private String age; // in months
    private String gender;
    private String color;
    private String weight; // in kg
    private String adoptionStatus; // default is "Available"
    @Type(type="text")
    private String description;
    @Type(type="text")
    private String vaccinationRecord;
    private LocalDateTime registeredAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "dog")
    @JsonIgnore
    private List<application> applications;

    

    public dog(long id, String name, String displayImagePath, String breed, Date birthDate, String age, String gender,
            String color, String weight, String adoptionStatus, String description, String vaccinationRecord,
            LocalDateTime registeredAt, LocalDateTime modifiedAt, List<application> applications) {
        this.id = id;
        this.name = name;
        this.displayImagePath = displayImagePath;
        this.breed = breed;
        this.birthDate = birthDate;
        this.age = age;
        this.gender = gender;
        this.color = color;
        this.weight = weight;
        this.adoptionStatus = adoptionStatus;
        this.description = description;
        this.vaccinationRecord = vaccinationRecord;
        this.registeredAt = registeredAt;
        this.modifiedAt = modifiedAt;
        this.applications = applications;
    }



    public dog() {
    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
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



    public Date getBirthDate() {
        return birthDate;
    }



    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }



    public String getAge() {
        return age;
    }



    public void setAge(String age) {
        this.age = age;
    }



    public String getGender() {
        return gender;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getColor() {
        return color;
    }



    public void setColor(String color) {
        this.color = color;
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



    public String getVaccinationRecord() {
        return vaccinationRecord;
    }



    public void setVaccinationRecord(String vaccinationRecord) {
        this.vaccinationRecord = vaccinationRecord;
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
