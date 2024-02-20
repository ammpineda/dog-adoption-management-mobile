package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.frontend.model.dog;
import com.project.frontend.retrofit.DogApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPetDetailsFromApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pet_details_from_app);

        int dogId = getIntent().getIntExtra("dogId", -1);
        Log.d("ShowPetDetails", "Received Dog ID: " + dogId);

        EditText petNameOutputView = findViewById(R.id.pet_name_output_view);
        EditText petBreedOutputView = findViewById(R.id.pet_breed_output_view);
        EditText petBdayOutputView = findViewById(R.id.pet_bday_output_view);
        EditText petGenderOutputView = findViewById(R.id.pet_gender_output_view);
        EditText petWeightOutputView = findViewById(R.id.pet_weight_output_view);
        TextView petDescriptionOutputView = findViewById(R.id.pet_description_output_view);
        ImageView petImageOutputView = findViewById(R.id.pet_image_view);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        Call<List<dog>> call = dogApi.getAllDogs();
        call.enqueue(new Callback<List<dog>>() {
            @Override
            public void onResponse(Call<List<dog>> call, Response<List<dog>> response) {
                if(response.isSuccessful()){
                    List<dog> dogs = response.body();
                    if(dogs!=null){
                        for (dog currentDog : dogs){
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

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<dog>> call, Throwable t) {

            }
        });
    }
}