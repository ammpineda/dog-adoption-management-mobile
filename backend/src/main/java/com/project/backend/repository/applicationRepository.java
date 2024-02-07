package com.project.backend.repository;

import com.project.backend.model.application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface applicationRepository extends CrudRepository<application, Integer> {
    @Query("SELECT a FROM application a WHERE a.referenceCode LIKE %:referenceCode%")
    List<application> findByReferenceCode(@Param("referenceCode") String referenceCode);
    
    @Query("SELECT a FROM application a WHERE CONCAT(a.dog.name) LIKE %:dog%")
    List<application> findByDogName(@Param("dog") String dogName);
    
    @Query("SELECT a FROM application a WHERE a.applicant.id LIKE %:id%")
    List<application> findByApplicantId(@Param("id") long id);
}
