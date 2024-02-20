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

import com.project.frontend.model.dog;

import java.util.ArrayList;

public class DogListAdapter extends ArrayAdapter<dog> {

    private Context c;
    private int r;

    public DogListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<dog> objects) {
        super(context, resource, objects);
        this.c = context;
        this.r = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        convertView = layoutInflater.inflate(r,parent, false);
        ImageView imageView = convertView.findViewById(R.id.show_dog_item_image_view);
        TextView txtName = convertView.findViewById(R.id.show_dog_item_name_view);
        imageView.setImageResource(getItem(position).getImage());
        txtName.setText(getItem(position).getName());

        return convertView;
    }


}
