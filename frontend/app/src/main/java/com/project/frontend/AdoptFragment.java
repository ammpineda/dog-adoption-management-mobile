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

import com.project.frontend.model.dog;
import com.project.frontend.retrofit.DogApi;
import com.project.frontend.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdoptFragment extends Fragment{

    private ListView mListView;
    private DogApi mDogApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_adopt, container, false);

        mListView = contentView.findViewById(R.id.show_applications_list_view);

        RetrofitService retrofitService = new RetrofitService();

        mDogApi = retrofitService.getRetrofit().create(DogApi.class);
        Call<List<dog>> call = mDogApi.getAllDogs();
        call.enqueue(new Callback<List<dog>>() {
            @Override
            public void onResponse(Call<List<dog>> call, Response<List<dog>> response) {
                if (response.isSuccessful()) {
                    List<dog> dogs = response.body();
                    if (dogs != null) {
                        int counter = 0;
                        for (dog currentDog : dogs) {
                            String displayImagePath = currentDog.getDisplayImagePath();
                            String imageName = displayImagePath.substring(0, displayImagePath.lastIndexOf('.'));
                            int resourceId = getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName());
                            currentDog.setImage(resourceId);
                            counter++;
                        }
                        DogListAdapter dogListAdapter = new DogListAdapter(getContext(), R.layout.show_dogs_list_row, (ArrayList<dog>) dogs);
                        mListView.setAdapter(dogListAdapter);
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


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dog clickedDog = (dog) parent.getItemAtPosition(position);
                long dogId = clickedDog.getId();
                Log.d("Clicked Dog ID", "Dog ID: " + dogId);


                Intent intent = new Intent(getActivity(), ShowPetDetails.class);
                intent.putExtra("dogId", dogId);
                startActivity(intent);
            }
        });

        return contentView;
    }


}