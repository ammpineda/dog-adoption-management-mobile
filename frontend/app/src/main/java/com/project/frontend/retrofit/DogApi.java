package com.project.frontend.retrofit;

import com.project.frontend.model.dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DogApi {

    @GET("/all-dogs")
    Call<List<dog>> getAllDogs();

    @POST("/create")
    Call<dog> createDog(@Body dog dog);

    @POST("/update-dog")
    Call<dog> updateDog(@Body dog dog);

    @DELETE("/delete-dog/{id}")
    Call<Void> deleteDog(@Path("id") int id);

    @GET("dogs/{id}")
    Call<dog> getDogById(@Path("id") int id);

}
