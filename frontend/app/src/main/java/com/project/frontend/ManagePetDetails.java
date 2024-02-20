package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.frontend.model.dog;
import com.project.frontend.retrofit.DogApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagePetDetails extends AppCompatActivity {

    private EditText petNameOutputView;
    private EditText petBreedOutputView;
    private EditText petBdayOutputView;
    private EditText petGenderOutputView;
    private EditText petWeightOutputView;
    private EditText petDescriptionOutputView;

    private ImageView petImageOutputView;
    private Button updateDogButton;
    private Button deleteDogButton;

    public int getRetrievedDogId() {
        return retrievedDogId;
    }

    public void setRetrievedDogId(int retrievedDogId) {
        this.retrievedDogId = retrievedDogId;
    }

    private int retrievedDogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pet_details);

        int dogId = (int) getIntent().getLongExtra("dogId", -1);
        setRetrievedDogId(dogId);
        Log.d("ShowPetDetails", "Received Dog ID: " + getRetrievedDogId());

        petNameOutputView = findViewById(R.id.pet_name_output_view);
        petBreedOutputView = findViewById(R.id.pet_breed_output_view);
        petBdayOutputView = findViewById(R.id.pet_bday_output_view);
        petGenderOutputView = findViewById(R.id.pet_gender_output_view);
        petWeightOutputView = findViewById(R.id.pet_weight_output_view);
        petDescriptionOutputView = findViewById(R.id.dog_description_output_view);
        petImageOutputView = findViewById(R.id.pet_image_view);
        updateDogButton = findViewById(R.id.update_button_view);
        deleteDogButton = findViewById(R.id.register_dog_button_view);


        RetrofitService retrofitService = new RetrofitService();
        DogApi mDogApi = retrofitService.getRetrofit().create(DogApi.class);

        Call<List<dog>> call = mDogApi.getAllDogs();
        call.enqueue(new Callback<List<dog>>() {
            @Override
            public void onResponse(Call<List<dog>> call, Response<List<dog>> response) {
                if (response.isSuccessful()) {
                    List<dog> dogs = response.body();
                    if (dogs != null) {
                        for (dog currentDog : dogs) {
                            if(currentDog.getId() == dogId){

                                String displayImagePath = currentDog.getDisplayImagePath();
                                String imageName = displayImagePath.substring(0, displayImagePath.lastIndexOf('.'));
                                int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                                petImageOutputView.setImageResource(resourceId);

                                petNameOutputView.setText(currentDog.getName());
                                petBreedOutputView.setText(currentDog.getBreed());

                                petBdayOutputView.setText(currentDog.getBirthDate());
                                petGenderOutputView.setText(currentDog.getGender());
                                petWeightOutputView.setText(currentDog.getWeight());
                                petDescriptionOutputView.setText(currentDog.getDescription());

                                deleteDogButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Call<Void> call = mDogApi.deleteDog(currentDog.getId());
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                Toast.makeText(ManagePetDetails.this, "Dog record deleted successfully!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ManagePetDetails.this, AdminNavigationBarActivity.class);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                Toast.makeText(ManagePetDetails.this, "Dog record delete failed!", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }
                                });

                                updateDogButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dog updatedDog = new dog();

                                        updatedDog.setId(getRetrievedDogId()); //important
                                        updatedDog.setDisplayImagePath(currentDog.getDisplayImagePath());
                                        updatedDog.setRegisteredAt(currentDog.getRegisteredAt());
                                        updatedDog.setName(String.valueOf(petNameOutputView.getText()));
                                        updatedDog.setBreed(String.valueOf(petBreedOutputView.getText()));
                                        updatedDog.setBirthDate(String.valueOf(petBdayOutputView.getText()));
                                        updatedDog.setGender(String.valueOf(petGenderOutputView.getText()));
                                        updatedDog.setWeight(String.valueOf(petWeightOutputView.getText()));
                                        updatedDog.setDescription(petDescriptionOutputView.getText().toString());
                                        Log.d("Testing", updatedDog.getDescription());

                                        Call<dog> call = mDogApi.updateDog(updatedDog);
                                        call.enqueue(new Callback<dog>() {
                                            @Override
                                            public void onResponse(Call<dog> call, Response<dog> response) {
                                                Toast.makeText(ManagePetDetails.this, "Dog updated successful!", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<dog> call, Throwable t) {
                                                Toast.makeText(ManagePetDetails.this, "Dog update failed!", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }
                                });


                            }
                        }
                    } else {
                        Log.e("API Error", "Response body is null");
                    }
                } else {
                    // handles unsuccessful
                    Log.e("API Error", "Failed to retrieve dogs from the API");
                }
            }

            @Override
            public void onFailure(Call<List<dog>> call, Throwable t) {
                Log.e("API Error", "Failed to make API call", t);
            }
        });



    }
}