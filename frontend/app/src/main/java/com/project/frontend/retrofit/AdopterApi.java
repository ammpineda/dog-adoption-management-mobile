package com.project.frontend.retrofit;

import com.project.frontend.model.admin;
import com.project.frontend.model.adopter;
import com.project.frontend.model.dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AdopterApi {

    @GET("adopter/latest-id")
    Call<Integer> getLatestAdopterId();

    @GET("/all-adopters")
    Call<List<adopter>> getAllAdopters();

    @POST("/add-adopter")
    Call<adopter> createAdopter(@Body adopter adopter);
}
