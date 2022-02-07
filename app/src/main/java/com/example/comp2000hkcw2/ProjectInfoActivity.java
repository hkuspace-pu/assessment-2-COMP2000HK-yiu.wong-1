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

    Bundle intentExtra;

    private Service service;

    private TextView tvProjectId;
    private TextView tvStudentId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvYear;
    private TextView tvThumbnailURL;
    private TextView tvPosterURL;
    private ImageView ivPhoto;

    private Button btnPIHome;
    private Button btnPIEditInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        this.service = ServiceGenerator.getInstance().getService();

        btnPIHome.setOnClickListener(View -> {
            Intent i = new Intent(ProjectInfoActivity.this, MainActivity.class);
        });

        btnPIEditInfo.setOnClickListener(View -> {

        });

        this.tvProjectId = (TextView) findViewById(R.id.tvPIProjectId);
        this.tvStudentId = (TextView) findViewById(R.id.tvPIStudentId);
        this.tvFirstName = (TextView) findViewById(R.id.tvPIFirstName);
        this.tvLastName = (TextView) findViewById(R.id.tvPILastName);
        this.tvTitle = (TextView) findViewById(R.id.tvPITitle);
        this.tvDesc = (TextView) findViewById(R.id.tvPIDesc);
        this.tvYear = (TextView) findViewById(R.id.tvPIYear);
        this.tvThumbnailURL = (TextView) findViewById(R.id.tvPIThumbnailURL);
        this.tvPosterURL = (TextView) findViewById(R.id.tvPIPosterURL);
        this.ivPhoto = (ImageView) findViewById(R.id.ivPIPhoto);

        intentExtra = getIntent().getExtras();

        if(intentExtra != null) {
            String projectIDMA = intentExtra.getString("maProjectID");
            tvProjectId.setText(projectIDMA);
        }

        Intent intent = getIntent();
        Integer projectId = intent.getIntExtra(R.id.tvPIProjectId);

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
                        tvFirstName.setText(project.getFirst_Name());
                    }

                    if(project.getSecond_Name() != null) {
                        tvLastName.setText(project.getSecond_Name());
                    }

                    if(project.getTitle() != null) {
                        tvTitle.setText(project.getTitle());
                    }

                    if(project.getDescription() != null) {
                        tvDesc.setText(project.getDescription());
                    }

                    if(project.getYear() != null) {
                        tvYear.setText(project.getYear().toString());
                    }

                    if(project.getThumbnailURL() != null) {
                        tvThumbnailURL.setText(project.getThumbnailURL());
                    }

                    if(project.getPosterURL() != null) {
                        tvPosterURL.setText(project.getPosterURL());
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