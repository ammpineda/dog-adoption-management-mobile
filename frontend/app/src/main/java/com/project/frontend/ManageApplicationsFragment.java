package com.project.frontend;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.project.frontend.model.application;
import com.project.frontend.retrofit.ApplicationApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageApplicationsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_manage_applications, container, false);

        ListView mListView = contentView.findViewById(R.id.show_applications_list_view);

        RetrofitService retrofitService = new RetrofitService();

        ApplicationApi applicationApi = retrofitService.getRetrofit().create(ApplicationApi.class);
        Call<List<application>> call = applicationApi.getAllApplications();
        call.enqueue(new Callback<List<application>>() {
            @Override
            public void onResponse(Call<List<application>> call, Response<List<application>> response) {
                if(response.isSuccessful()){
                    List<application> applications = response.body();
                    if(applications!=null){
                        ApplicationListAdapter applicationListAdapter = new ApplicationListAdapter(getContext(), R.layout.admin_show_applications_list_row, (ArrayList<application>) applications);
                        mListView.setAdapter(applicationListAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<application>> call, Throwable t) {
                Log.e("Error","Details: ", t);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                application clickedApp = (application) parent.getItemAtPosition(position);
                int appId = clickedApp.getId();
                Log.d("Clicked Application ID", "Application ID: " + appId);

                Intent intent = new Intent(contentView.getContext(), ManageApplicationDetails.class);
                intent.putExtra("appId", appId);
                startActivity(intent);
            }
        });


        return contentView;

    }
}