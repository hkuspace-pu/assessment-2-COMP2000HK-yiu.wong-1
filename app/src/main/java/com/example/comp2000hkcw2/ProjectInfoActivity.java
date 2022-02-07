package com.example.comp2000hkcw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.comp2000hkcw2.controller.ServiceGenerator;
import com.example.comp2000hkcw2.controller.Service;
import com.example.comp2000hkcw2.model.Project;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectInfoActivity extends AppCompatActivity {

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

        btnPIHome.setOnClickListener(View -> gotoBackHome());
        btnPIEditInfo.setOnClickListener(View -> gotoEditInfo());

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

        Intent intent = getIntent();
        Integer projectId = intent.getIntExtra("projectId", -1);

        Project project = new Project();
        project.setTitle("AAA");

        //call API to get project by projectId
        Call<Project> call = service.getProjectById(projectId);
        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project project = response.body();
                if (project == null) {
                    Log.d("[d]", "Project NOT Found!");
                } else {
                    tvProjectId.setText(project.getProjectID().toString());
                    tvStudentId.setText(project.getStudentID().toString());

                    if (project.getFirst_Name() != null) {
                        etFirstName.setText(project.getFirst_Name());
                    }

                    if (project.getSecond_Name() != null) {
                        etLastName.setText(project.getSecond_Name());
                    }

                    if (project.getTitle() != null) {
                        etTitle.setText(project.getTitle());
                    }

                    if (project.getDescription() != null) {
                        etDesc.setText(project.getDescription());
                    }

                    if (project.getYear() != null) {
                        etYear.setText(project.getYear().toString());
                    }

                    if (project.getThumbnailURL() != null) {
                        etThumbnailURL.setText(project.getThumbnailURL());
                    }

                    if (project.getPosterURL() != null) {
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
                Log.d("[d]", "Error: " + t);
            }
        });
    }

    public void gotoBackHome() {
        Intent intent = new Intent(ProjectInfoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void gotoEditInfo() {
            AlertDialog.Builder alert = new AlertDialog.Builder(ProjectInfoActivity.this);

            alert.setTitle("Edit Confirmation");
            alert.setMessage("CONFIRM Edit?");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {

                    Project project = new Project();
                    project.setTitle(etTitle.getText().toString());
                    project.setDescription(etDesc.getText().toString());
                    project.setYear(R.id.etCNYear);
                    project.setFirst_Name(etFirstName.getText().toString());
                    project.setSecond_Name(etLastName.getText().toString());
                    project.setPosterURL(etPosterURL.getText().toString());
                    project.setThumbnailURL(etThumbnailURL.getText().toString());


                    String str = tvProjectId.getText().toString();
                    Integer projectId = Integer.parseInt(str);
                    Call<Void> call = service.update(projectId, project);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            int code = response.code();
                            Log.d("[d]", "response status: " + code);
                            if (code == 204) {
                                //Log.d("[d]", "Project " + projectId + " Info Updated!");
                                Toast.makeText(getApplicationContext(), "Project Info Updated!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("[d]", "Error: " + t.toString());
                        }
                    });

                    dialog.dismiss();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

}