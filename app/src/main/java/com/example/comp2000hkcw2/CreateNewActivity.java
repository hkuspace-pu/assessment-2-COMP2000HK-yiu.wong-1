package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comp2000hkcw2.controller.ServiceGenerator;
import com.example.comp2000hkcw2.controller.Service;
import com.example.comp2000hkcw2.model.Project;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewActivity extends AppCompatActivity {

    private Service service;
    private EditText etStudentId;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etYear;

    Button btnCPCreateProj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_project);

        this.service = ServiceGenerator.getInstance().getService();

        btnCPCreateProj = findViewById(R.id.btnCProj);

        btnCPCreateProj.setOnClickListener(View -> gotoCreateProj());

        this.etStudentId = (EditText) findViewById(R.id.etCNStudentId);
        this.etFirstName = (EditText) findViewById(R.id.etCNFirstName);
        this.etLastName = (EditText) findViewById(R.id.etCNLastName);
        this.etTitle = (EditText) findViewById(R.id.etCNTitle);
        this.etDesc = (EditText) findViewById(R.id.etCNDesc);
        this.etYear = (EditText) findViewById(R.id.etCNYear);

    }

    public void gotoCreateProj() {
        Project p01 = new Project();
        p01.setStudentID(R.id.etCNStudentId);
        p01.setTitle(etTitle.getText().toString());
        p01.setDescription(etDesc.getText().toString());
        p01.setYear(R.id.etCNYear);
        p01.setFirst_Name(etFirstName.getText().toString());
        p01.setSecond_Name(etLastName.getText().toString());

        Call<Void> call = service.create(p01);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.d("[d]", "response status code: " + code);
                if (code == 201) {
                    Log.d("[d]", "Project Created!");
                    Toast.makeText(getApplicationContext(), "Project Created Successfully!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }
}
