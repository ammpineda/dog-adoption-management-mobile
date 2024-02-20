package com.project.frontend.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class adopter {
    private int id;
    private String fullName;
    private String email;
    private String contactNumber;
    private String homeAddress;
    private String birthDate;
    private String occupation;
    private String housingType;
    private String livingArrangement;
    private String reasonsToAdopt;
    private String registeredAt;
    private String updatedAt;

    private List<application> applications;

    public adopter(int id, String fullName, String email, String contactNumber, String homeAddress, String birthDate,
                   String occupation, String housingType, String livingArrangement, String reasonsToAdopt,
                   String registeredAt, String updatedAt, List<application> applications) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.homeAddress = homeAddress;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.housingType = housingType;
        this.livingArrangement = livingArrangement;
        this.reasonsToAdopt = reasonsToAdopt;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.applications = applications;
    }

    public adopter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public String getLivingArrangement() {
        return livingArrangement;
    }

    public void setLivingArrangement(String livingArrangement) {
        this.livingArrangement = livingArrangement;
    }

    public String getReasonsToAdopt() {
        return reasonsToAdopt;
    }

    public void setReasonsToAdopt(String reasonsToAdopt) {
        this.reasonsToAdopt = reasonsToAdopt;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<application> getApplications() {
        return applications;
    }

    public void setApplications(List<application> applications) {
        this.applications = applications;
    }
}
