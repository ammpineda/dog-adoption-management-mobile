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

import com.project.frontend.model.dog;

public class ViewApplicationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_view_application, container, false);

        EditText emailInput = contentView.findViewById(R.id.view_application_input_view);
        Button searchBtn = contentView.findViewById(R.id.view_application_button_view);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(emailInput.getText());
                Log.d("View Application by Email", "Input Email: " + email);


                Intent intent = new Intent(getActivity(), ViewApplicationDetails.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        return contentView;

    }
}