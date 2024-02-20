package com.project.frontend.retrofit;

import com.project.frontend.model.application;
import com.project.frontend.model.dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApplicationApi {

    @POST("/add-application")
    Call<application> createApplication(@Body application application);

    @GET("/all-applications")
    Call<List<application>> getAllApplications();

    @POST("/update-application")
    Call<application> updateApplication(@Body application application);
}
