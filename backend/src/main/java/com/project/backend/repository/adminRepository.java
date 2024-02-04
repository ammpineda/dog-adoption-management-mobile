package com.project.backend.repository;

import com.project.backend.model.admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends CrudRepository<admin, Integer> {

    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    admin findAdminByEmail(@Param("email") String email);

    @Query("SELECT a FROM Admin a WHERE a.password = :password")
    admin findAdminByPassword(@Param("password") String pass);

    @Query("SELECT a FROM admin a WHERE a.email = :email AND a.password = :password")
    admin findAdminByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
