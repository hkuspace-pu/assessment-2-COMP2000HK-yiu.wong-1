package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.comp2000hkcw2.controller.ServiceGenerator;
import com.example.comp2000hkcw2.controller.Interface;

public class CreateNewProjectActivity extends AppCompatActivity {

    private Interface anInterface;

    private EditText etStudentId;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etYear;

    private Button btnCreateProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_project);

        this.anInterface = ServiceGenerator.getInstance().getService();

        this.etStudentId = (EditText) findViewById(R.id.etStudentId);
        this.etFirstName = (EditText) findViewById(R.id.etFirstName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etTitle = (EditText) findViewById(R.id.etTitle);
        this.etDesc = (EditText) findViewById(R.id.etDesc);
        this.etYear = (EditText) findViewById(R.id.etYear);

    }
}