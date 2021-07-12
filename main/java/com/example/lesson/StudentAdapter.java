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
import models.Student;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private ArrayList<Student> students;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.students = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(resource, null);
        TextView lblStudentTitle = v.findViewById(R.id.lblStudentName);
        TextView lblStudentGrade = v.findViewById(R.id.lblStudentGrade);
        Student student = this.getItem(position);
        lblStudentTitle.setText(student.getName());
        lblStudentGrade.setText(student.getGrade() + "");
        v.setTag(student.getKey());

        return v;
    }
}
