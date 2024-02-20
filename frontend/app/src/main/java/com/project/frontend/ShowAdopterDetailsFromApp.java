package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.project.frontend.model.adopter;
import com.project.frontend.retrofit.AdopterApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAdopterDetailsFromApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adopter_details_from_app);

        int adopterId = getIntent().getIntExtra("adopterId", -1);
        Log.d("ShowAdopterDetails", "Received Adopter ID: " + adopterId);

        EditText fullNameDisplay = findViewById(R.id.adopter_name_output_view);
        EditText emailDisplay = findViewById(R.id.adopter_email_output_view);
        EditText phoneDisplay = findViewById(R.id.adopter_phone_output_view);
        EditText addressDisplay = findViewById(R.id.adopter_home_output_view);
        EditText occupationDisplay = findViewById(R.id.adopter_job_output_view);
        EditText birthdayDisplay = findViewById(R.id.adopter_birthday_output_view);
        EditText housingTypeDisplay = findViewById(R.id.adopter_housing_type_output_view);
        EditText reasonsDisplay = findViewById(R.id.adopter_reasons_output_view);

        RetrofitService retrofitService = new RetrofitService();
        AdopterApi adopterApi = retrofitService.getRetrofit().create(AdopterApi.class);

        Call<List<adopter>> call = adopterApi.getAllAdopters();
        call.enqueue(new Callback<List<adopter>>() {
            @Override
            public void onResponse(Call<List<adopter>> call, Response<List<adopter>> response) {
                if(response.isSuccessful()){
                    List<adopter> adopters = response.body();
                    if(adopters!=null){
                        for(adopter currentAdopter : adopters){

                            if(currentAdopter.getId() == adopterId){
                                fullNameDisplay.setText(currentAdopter.getFullName());
                                emailDisplay.setText(currentAdopter.getEmail());
                                phoneDisplay.setText(currentAdopter.getContactNumber());
                                addressDisplay.setText(currentAdopter.getHomeAddress());
                                occupationDisplay.setText(currentAdopter.getOccupation());
                                birthdayDisplay.setText(currentAdopter.getBirthDate());
                                housingTypeDisplay.setText(currentAdopter.getHousingType());
                                reasonsDisplay.setText(currentAdopter.getReasonsToAdopt());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<adopter>> call, Throwable t) {

            }
        });

    }
}