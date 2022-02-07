package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.comp2000hkcw2.controller.Service;
import com.example.comp2000hkcw2.controller.ServiceGenerator;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoUploadActivity extends AppCompatActivity {

    private Service service;
    private ImageView ivPUImage;
    Button btnPUUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);

        this.service = ServiceGenerator.getInstance().getService();

        ivPUImage = findViewById(R.id.ivProjPhoto);
        btnPUUpload = findViewById(R.id.btnUImg);

        btnPUUpload.setOnClickListener(View -> {

            File file = new File("/storage/emulated/0/Download/fireb01.jpg");
            if (!file.exists()) {
                Log.e("[d]", "Image NOT found!");
                Toast.makeText(getApplicationContext(), "IMAGE NOT FOUND!", Toast.LENGTH_LONG).show();
            } else {
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "file");

                Log.d("[d]", "on click XXXXXXXXXXXXXXXX");

                Intent intent = getIntent();
                Integer projectId = intent.getIntExtra("projectId", -1);
                Call<ResponseBody> call = service.uploadImage(projectId, requestBody, part);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        int code = response.code();
                        Log.d("[d]", "response status code: " + code);
                        if (code == 201) {
                            //Log.d("[d]", "Image " + file.getAbsolutePath() + " Uploaded");
                            Toast.makeText(getApplicationContext(), "Photo Uploaded Successfully!!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(PhotoUploadActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.d("[d]", "Error: " + t.toString());
                    }
                });
            }
        });
    }
}