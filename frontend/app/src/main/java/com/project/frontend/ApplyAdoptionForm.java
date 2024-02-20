package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.frontend.model.adopter;
import com.project.frontend.model.application;
import com.project.frontend.model.dog;
import com.project.frontend.retrofit.AdopterApi;
import com.project.frontend.retrofit.ApplicationApi;
import com.project.frontend.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApplyAdoptionForm extends AppCompatActivity {

    int retrievedAdopterId, retrievedDogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_adoption_form);

        int dogId = (int) getIntent().getLongExtra("dogId", -1);
        Log.d("ApplyAdoptionDetails", "Received Dog ID: " + dogId);
        setRetrievedDogId(dogId);

        Button cancelBtn = findViewById(R.id.cancel_button_view);
        Button submitBtn = findViewById(R.id.submit_button_view);


        RetrofitService retrofitService = new RetrofitService();
        AdopterApi adopterApi = retrofitService.getRetrofit().create(AdopterApi.class);
        Call<Integer> call = adopterApi.getLatestAdopterId();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    int newId = response.body();
                    newId = newId + 1; // for the new adopter

                    Log.e("New Adopter ID", "ID: " + newId);
                    setRetrievedAdopterId(newId);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("API Error", "Failed to retrieve dogs from the API");}
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplyAdoptionForm.this, UserNavigationBarActivity.class);
                startActivity(intent);
            }
        });


        submitBtn.setOnClickListener(view->{
            EditText fullName = findViewById(R.id.adopter_name_output_view);
            EditText email = findViewById(R.id.adopter_email_output_view);
            EditText contactNumber = findViewById(R.id.adopter_phone_output_view);
            EditText homeAddress = findViewById(R.id.adopter_home_output_view);
            EditText birthDate = findViewById(R.id.adopter_birthday_output_view);
            EditText occupation = findViewById(R.id.adopter_job_output_view);
            EditText housingType = findViewById(R.id.adopter_housing_type_output_view);
            EditText reasonsToAdopt = findViewById(R.id.adopter_reasons_output_view);

            String fullNameText = fullName.getText().toString().trim();
            String emailText = email.getText().toString().trim();
            String contactNumberText = contactNumber.getText().toString().trim();
            String homeAddressText = homeAddress.getText().toString().trim();
            String birthDateText = birthDate.getText().toString().trim();
            String occupationText = occupation.getText().toString().trim();
            String housingTypeText = housingType.getText().toString().trim();
            String reasonsToAdoptText = reasonsToAdopt.getText().toString().trim();

            if (fullNameText.isEmpty() || emailText.isEmpty() || contactNumberText.isEmpty() ||
                    homeAddressText.isEmpty() || birthDateText.isEmpty() || occupationText.isEmpty() ||
                    housingTypeText.isEmpty() || reasonsToAdoptText.isEmpty()) {
                Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT).show();
            } else {
                adopter adopter = new adopter();
                adopter.setId(getAdopterId());
                adopter.setFullName(String.valueOf(fullName.getText()));
                adopter.setEmail(String.valueOf(email.getText()));
                adopter.setContactNumber(String.valueOf(contactNumber.getText()));
                adopter.setHomeAddress(String.valueOf(homeAddress.getText()));
                adopter.setBirthDate(String.valueOf(birthDate.getText()));
                adopter.setOccupation(String.valueOf(occupation.getText()));
                adopter.setHousingType(String.valueOf(housingType.getText()));
                adopter.setReasonsToAdopt(String.valueOf(reasonsToAdopt.getText()));

                adopterApi.createAdopter(adopter)
                        .enqueue(new Callback<com.project.frontend.model.adopter>() {
                            @Override
                            public void onResponse(Call<com.project.frontend.model.adopter> call, Response<com.project.frontend.model.adopter> response) {

                                createApplication(getAdopterId(), getDogId());
                            }

                            @Override
                            public void onFailure(Call<com.project.frontend.model.adopter> call, Throwable t) {
                                Log.e("ADOPTER API Error", "Failed to make API call", t);
                            }
                        });

            }

        });

    }

    private void createApplication(int adopterId, int dogId){
        application app = new application();

        dog newDog = new dog();
        newDog.setId(dogId);

        adopter newAdopter = new adopter();
        newAdopter.setId(adopterId);

        app.setDog(newDog);
        app.setApplicant(newAdopter);

        RetrofitService retrofitService = new RetrofitService();
        ApplicationApi applicationApi = retrofitService.getRetrofit().create(ApplicationApi.class);
        applicationApi.createApplication(app).enqueue(new Callback<application>() {
            @Override
            public void onResponse(Call<application> call, Response<application> response) {
                Intent intent = new Intent(ApplyAdoptionForm.this, UserNavigationBarActivity.class);
                startActivity(intent);
                Toast.makeText(ApplyAdoptionForm.this, "Application submitted successful!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<application> call, Throwable t) {
                Log.e("APPLICATION API Error", "Failed to make API call", t);

            }
        });

    }
    public int getAdopterId() {
        return retrievedAdopterId;
    }

    public void setRetrievedAdopterId(int id){
        this.retrievedAdopterId = id;
    }


    public int getDogId(){
        return retrievedDogId;
    }

    public void setRetrievedDogId(int id){
        this.retrievedDogId = id;
    }
}