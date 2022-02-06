package com.example.comp2000hkcw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp2000hkcw2.controller.ServiceGenerator;
import com.example.comp2000hkcw2.controller.Interface;
import com.example.comp2000hkcw2.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllProjectsActivity extends AppCompatActivity {

    private Interface service;
    private RecyclerView projectRV;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private List<Project> projectList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_projects);

        projectRV = (RecyclerView) findViewById(R.id.rvProject);
        progressBar = (ProgressBar) findViewById(R.id.pbLoading);

        linearLayoutManager = new LinearLayoutManager(ShowAllProjectsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        projectRV.setLayoutManager(linearLayoutManager);

        this.service = ServiceGenerator.getInstance().getService();
        Call<List<Project>> call = service.getAllProjects();
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                progressBar.setVisibility(View.GONE);
                projectList = response.body();
                recyclerViewAdapter = new RecyclerViewAdapter(ShowAllProjectsActivity.this, projectList);
                projectRV.setAdapter(recyclerViewAdapter);

                //check if the project list is empty
               /*  int code = response.code();
                Log.d("[d]", "response status code: " + code);
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

                        progressBar.setVisibility(View.GONE);

                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(ShowAllProjectsActivity.this, projectList);
                        LinearLayoutManager llm = new LinearLayoutManager(ShowAllProjectsActivity.this);
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        projectRV.setAdapter(recyclerViewAdapter);
                        projectRV.setLayoutManager(llm);
                    }
                }

                */
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.d("[d]", "Error: " + t.toString());
                Toast.makeText(ShowAllProjectsActivity.this, "NO PROJECT FOUND!", Toast.LENGTH_LONG).show();
            }
        });
    }
}