package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.frontend.model.dog;
import com.project.frontend.retrofit.DogApi;
import com.project.frontend.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDogForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog_form);

        EditText petNameOutputView = findViewById(R.id.pet_name_output_view);
        EditText petBreedOutputView = findViewById(R.id.pet_breed_output_view);
        EditText petBdayOutputView = findViewById(R.id.pet_bday_output_view);
        EditText petGenderOutputView = findViewById(R.id.pet_gender_output_view);
        EditText petWeightOutputView = findViewById(R.id.pet_weight_output_view);
        EditText petDescriptionOutputView = findViewById(R.id.pet_traits_output_view);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);



        Button registerButton = findViewById(R.id.register_dog_button_view);
        Button cancelButton = findViewById(R.id.cancel_add_dog_button_view);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dog newDog = new dog();
                newDog.setDisplayImagePath("dog_default.png");
                newDog.setName(String.valueOf(petNameOutputView.getText()));
                newDog.setBreed(String.valueOf(petBreedOutputView.getText()));
                newDog.setBirthDate(String.valueOf(petBdayOutputView.getText()));
                newDog.setGender(String.valueOf(petGenderOutputView.getText()));
                newDog.setWeight(String.valueOf(petWeightOutputView.getText()));
                newDog.setDescription(String.valueOf(petDescriptionOutputView.getText()));

                Call<dog> call = dogApi.createDog(newDog);
                call.enqueue(new Callback<dog>() {
                    @Override
                    public void onResponse(Call<dog> call, Response<dog> response) {
                        Toast.makeText(AddDogForm.this, "New dog record has been registered!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddDogForm.this, AdminNavigationBarActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<dog> call, Throwable t) {
                        Toast.makeText(AddDogForm.this, "Dog registration failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDogForm.this, AdminNavigationBarActivity.class);
                startActivity(intent);
            }
        });


    }
}