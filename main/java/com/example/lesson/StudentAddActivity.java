package com.example.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.Student;

public class StudentAddActivity extends AppCompatActivity {
    EditText txtName, txtGroupKey, txtGrade, txtStudentKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtName = findViewById(R.id.txtName);
        txtGroupKey = findViewById(R.id.txtGroupKey);
        txtGrade = findViewById(R.id.txtGrade);

    }

    public void btnStudentSave(View v){
        UserDataProvider provider = UserDataProvider.getInstance();
        String name = txtName.getText().toString();
        String groupKey = txtGroupKey.getText().toString();
        int grade = Integer.parseInt(txtGrade.getText().toString());
        provider.addStudent(name, groupKey, grade);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}