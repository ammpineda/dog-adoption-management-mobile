package com.project.frontend.retrofit;

import com.project.frontend.model.admin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AdminApi {
    @GET("/get-admins")
    Call<List<admin>> getAllAdmins();

    @POST("/add-admin")
    Call<admin> createAdmin(@Body admin admin);
}
