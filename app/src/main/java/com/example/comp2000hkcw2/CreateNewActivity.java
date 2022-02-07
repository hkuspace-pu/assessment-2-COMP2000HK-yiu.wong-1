package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;

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

    private Button btnCreateProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_project);

        this.service = ServiceGenerator.getInstance().getService();

        this.etStudentId = (EditText) findViewById(R.id.etCNStudentId);
        this.etFirstName = (EditText) findViewById(R.id.etCNFirstName);
        this.etLastName = (EditText) findViewById(R.id.etCNLastName);
        this.etTitle = (EditText) findViewById(R.id.etCNTitle);
        this.etDesc = (EditText) findViewById(R.id.etCNDesc);
        this.etYear = (EditText) findViewById(R.id.etCNYear);

        Project p = new Project();
        p.setStudentID(getText().toString());

        btnCreateProject.setOnClickListener(View -> {
            Call<Void> call = service.create(p);
            call.enqueue(new Callback<Void>() {
                @Override
                //Asynctask
                public void onResponse(Call<Void> call, Response<Void> response) {
                    int code = response.code();
                    Log.d("[d]", "response status code: " + code);
                    if (code == 201)
                    {
                        Log.d("[d]", "Project Created!");
                        Toast.makeText(getApplicationContext(), "Project Created Successfully!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("[d]", "Error: " + t.toString());
                }
            });
        });
    }
}