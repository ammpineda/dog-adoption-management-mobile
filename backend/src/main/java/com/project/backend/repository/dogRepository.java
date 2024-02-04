package com.project.backend.repository;

import com.project.backend.model.dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface dogRepository extends CrudRepository<dog, Integer> {
    @Query("SELECT a FROM dog a WHERE CONCAT(a.name) LIKE %:name%")
    List<dog> findByName(@Param("name") String name);

    @Query("SELECT a FROM dog a WHERE CONCAT(a.adoptionStatus) LIKE %:adoptionStatus%")
    List<dog> findByAdoptionStatus(@Param("adoptionStatus")String adoptionStatus);
}
