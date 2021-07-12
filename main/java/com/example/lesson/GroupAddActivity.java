package com.example.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import models.Student;

public class GroupAddActivity extends AppCompatActivity {
    EditText txtTitle, txtKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        txtTitle = findViewById(R.id.txtGroupTitle);
        txtKey = findViewById(R.id.txtGroupKey);
    }

    public void btnGroupSave(View v){
        UserDataProvider provider = UserDataProvider.getInstance();
        String title = txtTitle.getText().toString();
        String key = txtKey.getText().toString();
        provider.addGroup(title , key);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}