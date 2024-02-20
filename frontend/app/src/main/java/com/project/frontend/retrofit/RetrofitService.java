package com.project.frontend.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // Set the desired date format here
                .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.100.235:8080") //change baseURL accordingly
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

    }
    public Retrofit getRetrofit(){
        return retrofit;
    }

}
