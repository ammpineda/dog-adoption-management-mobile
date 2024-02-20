package com.project.frontend.model;

import java.time.LocalDateTime;

public class application {
    private int id;
    private String referenceCode;
    private String status;

    private String feedback;
    private String submittedAt;
    private String reviewedAt;
    private String resultsAt;
    private String modifiedAt;

    private adopter applicant;

    private dog dog;


    public application(int id, String referenceCode, String status, String feedback, String submittedAt,
                       String reviewedAt, String resultsAt, String modifiedAt, adopter applicant,
                       dog dog) {
        this.id = id;
        this.referenceCode = referenceCode;
        this.status = status;
        this.feedback = feedback;
        this.submittedAt = submittedAt;
        this.reviewedAt = reviewedAt;
        this.resultsAt = resultsAt;
        this.modifiedAt = modifiedAt;
        this.applicant = applicant;
        this.dog = dog;
    }


    public application() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getReferenceCode() {
        return referenceCode;
    }


    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getFeedback() {
        return feedback;
    }


    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public String getSubmittedAt() {
        return submittedAt;
    }


    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }


    public String getReviewedAt() {
        return reviewedAt;
    }


    public void setReviewedAt(String reviewedAt) {
        this.reviewedAt = reviewedAt;
    }


    public String getResultsAt() {
        return resultsAt;
    }


    public void setResultsAt(String resultsAt) {
        this.resultsAt = resultsAt;
    }


    public String getModifiedAt() {
        return modifiedAt;
    }


    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


    public adopter getApplicant() {
        return applicant;
    }


    public void setApplicant(adopter applicant) {
        this.applicant = applicant;
    }


    public dog getDog() {
        return dog;
    }


    public void setDog(dog dog) {
        this.dog = dog;
    }
}
