package com.example.lesson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import models.Student;

public class StudentFragment extends Fragment {
    private ListView studentsListView;
    private TextView groupName;

    private StudentAdapter studentsAdapter;
    private ArrayList<Student> studentArrayList;
    private String groupKey, groupTitle;
    private UserDataProvider provider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        studentsListView = view.findViewById(R.id.studentsListView);
        studentArrayList = new ArrayList<>();

        studentsAdapter = new StudentAdapter(getContext(), R.layout.student_item,
                studentArrayList);

        studentsListView.setAdapter(studentsAdapter);


        onStudentDataListener listener = new onStudentDataListener() {
            @Override
            public void onStudentListener(ArrayList<Student> students) {
                studentArrayList.clear();
                studentArrayList.addAll(students);
                studentsAdapter.notifyDataSetChanged();
            }
        };
        provider = UserDataProvider.getInstance();
        provider.getStudents(listener);



        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = (String)view.getTag();
                Intent intentClick = new Intent(getContext(), EditStudentActivity.class);
                intentClick.putExtra("studentKey", key);
                startActivity(intentClick);
            }
        });

        studentsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Attention!");
                builder.setMessage("Are you want to delete Student?");
                builder.setIcon(R.mipmap.ic_dialog);
                DialogInterface.OnClickListener listClick = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String key = (String)view.getTag();
                        for(Student s: studentArrayList){
                            if(s.getKey().equals(key)){
                                studentArrayList.remove(s);
                                provider.deleteStudent(s);
                                studentsAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }
                    }
                };
                builder.setPositiveButton("Yes", listClick);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }
}