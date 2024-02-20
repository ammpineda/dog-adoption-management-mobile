package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.frontend.model.application;
import com.project.frontend.retrofit.ApplicationApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageApplicationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_application_details);

        int clickedApplicationId = getIntent().getIntExtra("appId", -1);
        Log.d("ShowApplicationDetails", "Received ID: " + clickedApplicationId);

        RetrofitService retrofitService = new RetrofitService();
        ApplicationApi applicationApi = retrofitService.getRetrofit().create(ApplicationApi.class);
        Call<List<application>> call = applicationApi.getAllApplications();
        call.enqueue(new Callback<List<application>>() {
            @Override
            public void onResponse(Call<List<application>> call, Response<List<application>> response) {
                if (response.isSuccessful()) {
                    List<application> applications = response.body();
                    for (application application : applications) {
                        int compareID;
                        compareID = application.getId();

                        if(clickedApplicationId == compareID){

                            EditText referenceCodeDisplay = findViewById(R.id.application_reference_view);
                            EditText statusDisplay = findViewById(R.id.application_status_view);
                            EditText feedbackDisplay = findViewById(R.id.application_feedback_view);
                            EditText submittedDateDisplay = findViewById(R.id.application_submitted_date_view);
                            EditText modifiedDateDisplay = findViewById(R.id.application_modified_date_view);

                            referenceCodeDisplay.setText(application.getReferenceCode());
                            statusDisplay.setText(application.getStatus());
                            feedbackDisplay.setText(application.getFeedback());
                            submittedDateDisplay.setText(application.getSubmittedAt());
                            modifiedDateDisplay.setText(application.getModifiedAt());

                            Button updateButton = findViewById(R.id.update_application_view);

                            updateButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    application updatedApp = new application();
                                    updatedApp.setId(application.getId());
                                    updatedApp.setReferenceCode(String.valueOf(referenceCodeDisplay.getText()));
                                    updatedApp.setStatus(String.valueOf(statusDisplay.getText()));
                                    updatedApp.setApplicant(application.getApplicant());
                                    updatedApp.setDog(application.getDog());
                                    updatedApp.setSubmittedAt(String.valueOf(submittedDateDisplay.getText()));
                                    updatedApp.setFeedback(String.valueOf(feedbackDisplay.getText()));

                                    Call<application> call = applicationApi.updateApplication(updatedApp);
                                    call.enqueue(new Callback<com.project.frontend.model.application>() {
                                        @Override
                                        public void onResponse(Call<com.project.frontend.model.application> call, Response<com.project.frontend.model.application> response) {
                                            Toast.makeText(ManageApplicationDetails.this, "Application record updated successfully!", Toast.LENGTH_SHORT).show();                                        }

                                        @Override
                                        public void onFailure(Call<com.project.frontend.model.application> call, Throwable t) {

                                        }
                                    });
                                }
                            });

                            ImageView dogIcon = findViewById(R.id.dog_details_icon_view);
                            dogIcon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int dogId = application.getDog().getId();
                                    Log.d("Passed Dog ID", "Dog ID: " + dogId);
                                    Intent intent = new Intent(ManageApplicationDetails.this, ShowPetDetailsFromApp.class);
                                    intent.putExtra("dogId", dogId);
                                    startActivity(intent);
                                }
                            });

                            ImageView adopterIcon = findViewById(R.id.adopter_details_icon_view);
                            adopterIcon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int adopterId = application.getApplicant().getId();
                                    Log.d("Passed Adopter ID", "Adopter ID: " + adopterId);
                                    Intent intent = new Intent(ManageApplicationDetails.this, ShowAdopterDetailsFromApp.class);
                                    intent.putExtra("adopterId", adopterId);
                                    startActivity(intent);
                                }
                            });

                            break;

                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<application>> call, Throwable t) {
                // Handle failure
            }
        });

    }
}