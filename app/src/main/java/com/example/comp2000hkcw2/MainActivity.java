package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.comp2000hkcw2.controller.Manager;
import com.example.comp2000hkcw2.controller.Service;
import com.example.comp2000hkcw2.model.Project;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String CHANNEL_ID = "channel";
    private int notificationId = 100;
    private Service service;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        this.service = Manager.getInstance().getService();

        //showAllProjects();
        //getProjectById(3775);
        //createNewProject();
        //uploadImage(3775);
        //updateProject(3775);
        //deleteProject(3775);
    }

    public void showAllProjects() {

        Call<List<Project>> call = service.listAllProjects();
        call.enqueue(new Callback<List<Project>>() {
            @Override
            //Asynctask
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                List<Project> projectList = response.body();
                //check if project list is empty
                if (projectList == null)
                {
                    Log.d("[d]", "NO PROJECT FOUND!");
                    Toast.makeText(getApplicationContext(), "NO PROJECT FOUND", Toast.LENGTH_LONG).show();
                }
                else
                {
                    for (int i = 0; i < projectList.size(); i++)
                    {
                        Project project = projectList.get(i);
                        Log.d("[d]", project.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }

    public void getProjectById(Integer projectId) {
        Call<Project> call = service.getProjectById(projectId);
        call.enqueue(new Callback<Project>() {
            @Override
            //Asynctask
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project project = response.body();
                if (project == null)
                {
                    Log.d("[d]", "PROJECT NOT FOUND!");
                    Toast.makeText(getApplicationContext(), "PROJECT NOT FOUND!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.d("[d]", project.toString());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }

    public void createNewProject() {
        Project p01 = new Project();
        p01.setStudentID(1445996872);
        p01.setTitle("XOXOXOXOXOXOXOXOXOXOXOXOXOXOX");
        p01.setDescription("OXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
        p01.setYear(2022);
        p01.setFirst_Name("Adam");
        p01.setSecond_Name("Smith");

        Call<Void> call = service.create(p01);
        call.enqueue(new Callback<Void>() {
            @Override
            //Asynctask
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.d("[d]", "response status code: " + code);
                if (code == 201)
                {
                    Log.d("[d]", "Project Created!");
                    Toast.makeText(getApplicationContext(), "Project Created!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }

    public void uploadImage(Integer projectId) {

        File file = new File("/storage/emulated/0/Download/fireb01.jpg");
        if (!file.exists())
        {
            Log.e("[d]", "Image NOT found!");
            Toast.makeText(getApplicationContext(), "IMAGE NOT FOUND!", Toast.LENGTH_LONG).show();
        }
        else
        {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "file");

            Call<ResponseBody> call = service.uploadImage(projectId, requestBody, part);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    int code = response.code();
                    Log.d("[d]", "response status code: " + code);
                    if (code == 201)
                    {
                        //Log.d("[d]", "Image " + file.getAbsolutePath() + " Uploaded");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("[d]", "Error: " + t.toString());
                }
            });
        }
    }

    public void updateProject(Integer projectId) {
        Project p01 = new Project();
        p01.setTitle("AAAAAAAAABBBBBBBBBBBBCCCCCCCCCCCCCCCCC");
        p01.setDescription("AAA BBB CCC");
        p01.setYear(2021);
        p01.setThumbnailURL("http://www.google.com");
        p01.setPosterURL("http://www.google.com");

        Call<Void> call = service.update(projectId, p01);
        call.enqueue(new Callback<Void>() {
            @Override
            //Asynctask
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.d("[d]", "response status: " + code);
                if (code == 204)
                {
                    //Log.d("[d]", "Project " + projectId + " Info Updated!");
                    Toast.makeText(getApplicationContext(), "Project Info Updated!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }

    public void deleteProject(Integer projectId)
    {
        Call<Void> call = service.delete(projectId);
        call.enqueue(new Callback<Void>() {
            @Override
            //Asynctask
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.d("[d]", "response status code: " + code);
                if (code == 204)
                {
                    //Log.d("[d]", "Project " + projectId + " Deleted");
                    Toast.makeText(getApplicationContext(), "Project Deleted!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
            }
        });
    }

    public void imageUploadedNotification(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Image Upload")
                .setContentText("Upload in progress")
                .setSmallIcon(R.drawable.noti)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(notificationId, builder.build());

        builder.setContentText("Image Upload completed")
                .setProgress(0, 0, false);
        notificationManager.notify(notificationId, builder.build());

    }
    
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channel", importance);
            channel.setDescription("My Notification");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}