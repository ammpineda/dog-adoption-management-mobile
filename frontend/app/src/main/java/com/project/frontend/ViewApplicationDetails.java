package com.project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.frontend.model.adopter;
import com.project.frontend.model.application;
import com.project.frontend.model.dog;
import com.project.frontend.retrofit.ApplicationApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewApplicationDetails extends AppCompatActivity {

    public dog getDogToDisplay() {
        return dogToDisplay;
    }

    public void setDogToDisplay(dog dogToDisplay) {
        this.dogToDisplay = dogToDisplay;
    }

    public application getApplicationToDisplay() {
        return applicationToDisplay;
    }

    public void setApplicationToDisplay(application applicationToDisplay) {
        this.applicationToDisplay = applicationToDisplay;
    }

    public adopter getAdopterToDisplay() {
        return adopterToDisplay;
    }

    public void setAdopterToDisplay(adopter adopterToDisplay) {
        this.adopterToDisplay = adopterToDisplay;
    }

    dog dogToDisplay;
    application applicationToDisplay;
    adopter adopterToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_details);

        String inputEmail = getIntent().getStringExtra("email");
        Log.d("ShowApplicationDetails", "Received Email: " + inputEmail);
        RetrofitService retrofitService = new RetrofitService();
        ApplicationApi applicationApi = retrofitService.getRetrofit().create(ApplicationApi.class);
        Call<List<application>> call = applicationApi.getAllApplications();
        call.enqueue(new Callback<List<application>>() {
            @Override
            public void onResponse(Call<List<application>> call, Response<List<application>> response) {
                if (response.isSuccessful()) {
                    List<application> applications = response.body();
                    for (application application : applications) {
                        String compareEmail;
                        compareEmail = application.getApplicant().getEmail();
                        Log.d("ApplicationDetails", "Applicant Email: " + application.getApplicant().getEmail());

                        // if input email matches
                        if(inputEmail.equals(compareEmail)){
                            Log.d("CORRECT", "CORRECT");
                            Toast.makeText(ViewApplicationDetails.this, "Application retrieved successfully!", Toast.LENGTH_SHORT).show();

                            setApplicationToDisplay(application);
                            setDogToDisplay(application.getDog());
                            setAdopterToDisplay(application.getApplicant());


                            EditText referenceCodeDisplay = findViewById(R.id.application_reference_view);
                            EditText statusDisplay = findViewById(R.id.application_status_view);
                            EditText feedbackDisplay = findViewById(R.id.application_feedback_view);
                            EditText submittedDateDisplay = findViewById(R.id.application_submitted_date_view);
                            EditText modifiedDateDisplay = findViewById(R.id.application_modified_date_view);

                            referenceCodeDisplay.setText(applicationToDisplay.getReferenceCode());
                            statusDisplay.setText(applicationToDisplay.getStatus());
                            feedbackDisplay.setText(applicationToDisplay.getFeedback());
                            submittedDateDisplay.setText(applicationToDisplay.getSubmittedAt());
                            modifiedDateDisplay.setText(applicationToDisplay.getModifiedAt());

                            ImageView dogIcon = findViewById(R.id.dog_details_icon_view);
                            dogIcon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int dogId = getDogToDisplay().getId();
                                    Log.d("Passed Dog ID", "Dog ID: " + dogId);
                                    Intent intent = new Intent(ViewApplicationDetails.this, ShowPetDetailsFromApp.class);
                                    intent.putExtra("dogId", dogId);
                                    startActivity(intent);
                                }
                            });

                            ImageView adopterIcon = findViewById(R.id.adopter_details_icon_view);
                            adopterIcon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int adopterId = getAdopterToDisplay().getId();
                                    Log.d("Passed Adopter ID", "Adopter ID: " + adopterId);
                                    Intent intent = new Intent(ViewApplicationDetails.this, ShowAdopterDetailsFromApp.class);
                                    intent.putExtra("adopterId", adopterId);
                                    startActivity(intent);
                                }
                            });

                            break;

                        } else {
                            EditText feedbackDisplay = findViewById(R.id.application_feedback_view);
                            feedbackDisplay.setText("The application associated with the entered email does not exist.");

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