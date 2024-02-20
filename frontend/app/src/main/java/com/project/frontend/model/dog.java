package com.project.frontend.model;

import java.util.List;

public class dog {

    private int id;
    private String name;
    private String displayImagePath;
    private String breed;
    private String birthDate;
    private String gender;
    private String color;
    private String weight; // in kg
    private String adoptionStatus = "Available"; // default is "Available"

    private String description;

    private String vaccinationRecord;
    private String registeredAt;
    private String modifiedAt;

    private List<application> applications;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;



    public dog(int id, String name, String displayImagePath, String breed, String birthDate, String gender,
               String color, String weight, String adoptionStatus, String description, String vaccinationRecord,
               String registeredAt, String modifiedAt, List<application> applications, int image) {
        this.id = id;
        this.name = name;
        this.displayImagePath = displayImagePath;
        this.breed = breed;
        this.birthDate = birthDate;
        this.gender = gender;
        this.color = color;
        this.weight = weight;
        this.adoptionStatus = adoptionStatus;
        this.description = description;
        this.vaccinationRecord = vaccinationRecord;
        this.registeredAt = registeredAt;
        this.modifiedAt = modifiedAt;
        this.applications = applications;
        this.image = image;
    }



    public dog() {
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



    public String getRegisteredAt() {
        return registeredAt;
    }



    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }



    public String getModifiedAt() {
        return modifiedAt;
    }



    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }



    public List<application> getApplications() {
        return applications;
    }



    public void setApplications(List<application> applications) {
        this.applications = applications;
    }
}
