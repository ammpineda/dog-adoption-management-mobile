package com.project.frontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.frontend.model.application;

import java.util.ArrayList;

public class ApplicationListAdapter extends ArrayAdapter<application> {

    private Context c;
    private int r;

    public ApplicationListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<application> objects){
        super(context, resource, objects);
        this.c = context;
        this.r = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        convertView = layoutInflater.inflate(r,parent, false);
        TextView txtAdopter = convertView.findViewById(R.id.item_adopter_view);
        TextView txtDog = convertView.findViewById(R.id.item_pet_view);
        txtAdopter.setText(getItem(position).getApplicant().getFullName());
        txtDog.setText(getItem(position).getDog().getName());

        return convertView;
    }

}
