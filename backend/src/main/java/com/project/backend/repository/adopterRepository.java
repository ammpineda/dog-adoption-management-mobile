package com.project.backend.repository;

import com.project.backend.model.admin;
import com.project.backend.model.adopter;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface adopterRepository extends CrudRepository<adopter, Integer> {
    @Query("SELECT a FROM adopter a WHERE CONCAT(a.firstName, ' ', a.lastName) LIKE %:name%")
    List<adopter> findByName(@Param("name") String name);

    @Query("SELECT a FROM adopter a WHERE CONCAT(a.email) LIKE %:email%")
    List<adopter> findByEmail(@Param("email")String email);

    @Query("SELECT a FROM adopter a WHERE CONCAT(a.contactNumber) LIKE %:contact%")
    List<adopter> findByContactNumber(@Param("contact")String contactNumber);

    @Query("SELECT a FROM adopter a WHERE a.email = :email")
    adopter findUserByEmail(@Param("email") String email);

}
