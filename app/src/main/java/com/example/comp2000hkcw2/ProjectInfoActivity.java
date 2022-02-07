package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp2000hkcw2.controller.ServiceGenerator;
import com.example.comp2000hkcw2.controller.Service;
import com.example.comp2000hkcw2.model.Project;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectInfoActivity extends AppCompatActivity {

    //Bundle intentExtra;

    private Service service;

    private TextView tvProjectId;
    private TextView tvStudentId;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etYear;
    private EditText etThumbnailURL;
    private EditText etPosterURL;
    private ImageView ivPhoto;

    Button btnPIHome;
    Button btnPIEditInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        this.service = ServiceGenerator.getInstance().getService();

        btnPIHome = findViewById(R.id.btnPIHome);
        btnPIEditInfo = findViewById(R.id.btnPIEditInfo);

        btnPIHome.setOnClickListener(View -> {
           Intent intent = new Intent(ProjectInfoActivity.this, MainActivity.class);
           startActivity(intent);
        });

        btnPIEditInfo.setOnClickListener(View -> {

        });

        this.tvProjectId = (TextView) findViewById(R.id.tvPIProjectId);
        this.tvStudentId = (TextView) findViewById(R.id.tvPIStudentId);
        this.etFirstName = (EditText) findViewById(R.id.etPIFirstName);
        this.etLastName = (EditText) findViewById(R.id.etPILastName);
        this.etTitle = (EditText) findViewById(R.id.etPITitle);
        this.etDesc = (EditText) findViewById(R.id.etPIDesc);
        this.etYear = (EditText) findViewById(R.id.etPIYear);
        this.etThumbnailURL = (EditText) findViewById(R.id.etPIThumbnailURL);
        this.etPosterURL = (EditText) findViewById(R.id.etPIPosterURL);
        this.ivPhoto = (ImageView) findViewById(R.id.ivPIPhoto);

        /* intentExtra = getIntent().getExtras();
        if (intentExtra != null) {
            String projectIDMA = intentExtra.getString("maProjectID");
            tvProjectId.setText(projectIDMA);
        } */

        Intent intent = getIntent();
        Integer projectId = intent.getIntExtra("projectId", -1);

            //call API to get project by projectId
            Call<Project> call = service.getProjectById(projectId);
            call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project project = response.body();
                if (project == null)
                {
                    Log.d("[d]", "Project NOT Found!");
                }
                else
                {
                    tvProjectId.setText(project.getProjectID().toString());
                    tvStudentId.setText(project.getStudentID().toString());

                    if(project.getFirst_Name() != null) {
                        etFirstName.setText(project.getFirst_Name());
                    }

                    if(project.getSecond_Name() != null) {
                        etLastName.setText(project.getSecond_Name());
                    }

                    if(project.getTitle() != null) {
                        etTitle.setText(project.getTitle());
                    }

                    if(project.getDescription() != null) {
                        etDesc.setText(project.getDescription());
                    }

                    if(project.getYear() != null) {
                        etYear.setText(project.getYear().toString());
                    }

                    if(project.getThumbnailURL() != null) {
                        etThumbnailURL.setText(project.getThumbnailURL());
                    }

                    if(project.getPosterURL() != null) {
                        etPosterURL.setText(project.getPosterURL());
                    }

                    if (project.getPhoto() != null) {
                        String photoBase64Str = project.getPhoto();
                        byte[] bytes = Base64.decode(photoBase64Str, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        ivPhoto.setImageBitmap(bitmap);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }
}

