package com.example.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import models.Student;

public class EditStudentActivity extends AppCompatActivity {
    private String studentKey;
    private UserDataProvider provider;
    private ArrayList<Student> studentArrayList;
    private EditText txtEditName, txtEditGrade;
    private Student studentTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Intent intent = getIntent();
        studentKey = intent.getExtras().getString("studentKey");
        provider = UserDataProvider.getInstance();
        txtEditGrade = findViewById(R.id.txtEditGrade);
        txtEditName = findViewById(R.id.txtEditName);
        studentTemp = null;
        studentArrayList = new ArrayList<>();
    }

    public void btnEditPressed(View v){
        onStudentDataListener listener = new onStudentDataListener() {
            @Override
            public void onStudentListener(ArrayList<Student> students) {
                studentArrayList.clear();
                studentArrayList.addAll(students);
                for (Student student: studentArrayList){
                    if(student.getKey().equals(studentKey)){
                        studentTemp = student;
                        studentTemp.setGrade(Integer.parseInt(txtEditGrade.getText().toString()));
                        studentTemp.setName(txtEditName.getText().toString());
                        studentTemp.setKey(studentKey);
                        provider.updateStudent(studentTemp);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        provider.getStudents(listener);
    }
}