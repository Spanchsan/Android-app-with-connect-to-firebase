package com.example.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import models.Group;

public class GroupAdapter extends ArrayAdapter<Group> {
    private Context context;
    private int resource;
    private ArrayList<Group> groups;

    public GroupAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Group> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.groups = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(resource, null);
        TextView lblTitle = v.findViewById(R.id.lblTitle);
        TextView lblKey = v.findViewById(R.id.lblKey);
        Group group = this.getItem(position);
        lblTitle.setText(group.getTitle());
        lblKey.setText(group.getKey());

        v.setTag(group.getKey());

        return v;
    }
}
