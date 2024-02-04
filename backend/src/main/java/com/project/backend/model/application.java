package com.project.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="application")
public class application {
    @Id
    private long id;
    private String referenceCode;
    private String status;
    @Type(type="text")
    private String feedback;
    private LocalDateTime submittedAt;
    private LocalDateTime reviewedAt;
    private LocalDateTime resultsAt;
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private adopter applicant;

    @ManyToOne
    @JoinColumn(name="dog_id")
    private dog dog;


    public application(long id, String referenceCode, String status, String feedback, LocalDateTime submittedAt,
            LocalDateTime reviewedAt, LocalDateTime resultsAt, LocalDateTime modifiedAt, adopter applicant,
            com.project.backend.model.dog dog) {
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


    public long getId() {
        return id;
    }


    public void setId(long id) {
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


    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }


    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }


    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }


    public void setReviewedAt(LocalDateTime reviewedAt) {
        this.reviewedAt = reviewedAt;
    }


    public LocalDateTime getResultsAt() {
        return resultsAt;
    }


    public void setResultsAt(LocalDateTime resultsAt) {
        this.resultsAt = resultsAt;
    }


    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }


    public void setModifiedAt(LocalDateTime modifiedAt) {
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
