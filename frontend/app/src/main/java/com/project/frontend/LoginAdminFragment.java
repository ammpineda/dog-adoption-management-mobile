package com.project.frontend;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.frontend.model.admin;
import com.project.frontend.retrofit.AdminApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAdminFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_login_admin, container, false);
        RetrofitService retrofitService = new RetrofitService();
        AdminApi adminApi = retrofitService.getRetrofit().create(AdminApi.class);

        Call<List<admin>> call = adminApi.getAllAdmins();
        call.enqueue(new Callback<List<admin>>() {
            @Override
            public void onResponse(Call<List<admin>> call, Response<List<admin>> response) {
                if(response.isSuccessful()){
                    List<admin> admins = response.body();

                    Button loginButton = contentView.findViewById(R.id.login_button_view);
                    loginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(admins!=null){
                                for(admin currentAdmin : admins){

                                    EditText emailInput = contentView.findViewById(R.id.email_input_view);
                                    EditText passwordInput = contentView.findViewById(R.id.password_input_view);

                                    String email = String.valueOf(emailInput.getText());
                                    String password = String.valueOf(passwordInput.getText());

                                    if(currentAdmin.getEmail().equals(email) && currentAdmin.getPassword().equals(password)){
                                        Toast.makeText(contentView.getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), AdminNavigationBarActivity.class);
                                        startActivity(intent);

                                    } else{
                                        Toast.makeText(contentView.getContext(), "Login failed! Invalid credentials.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<admin>> call, Throwable t) {
                // handles error
            }
        });

        return contentView;
    }
}