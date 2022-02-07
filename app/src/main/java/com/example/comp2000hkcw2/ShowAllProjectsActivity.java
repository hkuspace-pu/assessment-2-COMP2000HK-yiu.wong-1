package com.example.comp2000hkcw2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp2000hkcw2.controller.Service;
import com.example.comp2000hkcw2.controller.ServiceGenerator;
import com.example.comp2000hkcw2.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllProjectsActivity extends AppCompatActivity {

    private Service service;
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_projects);

        this.service = ServiceGenerator.getInstance().getService();

        progressBar = findViewById(R.id.pbLoading);
        this.listView = findViewById(R.id.listview);

        Call<List<Project>> call = this.service.getAllProjects();
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                progressBar.setVisibility(View.GONE);
                List<Project> list = response.body();
                Project[] projects = new Project[list.size()];
                for (int i = 0; i < list.size(); i++)
                {
                    projects[i] = list.get(i);
                    Log.i("All Projects", projects[i].toString());
                }

                ArrayAdapter<Project> adapter = new ArrayAdapter<Project>(
                        ShowAllProjectsActivity.this,
                        android.R.layout.activity_list_item,
                        android.R.id.text1,
                        projects
                ) {

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        Project project = getItem(position);
                        TextView tv = view.findViewById(android.R.id.text1);
                        tv.setText("Project ID: " + project.getProjectID() + " | " + "Student ID: " + project.getStudentID() + " | " + "Title: " + project.getTitle());
                        return view;
                    }
                };

                listView.setAdapter(adapter);
                Toast.makeText(ShowAllProjectsActivity.this, "All Projects Loaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.e("[e]", "Error: " + t.toString());
                Toast.makeText(ShowAllProjectsActivity.this, "ERROR OCCURRED!", Toast.LENGTH_LONG).show();
            }
        });
    }
}



/*  private Service service;
    private RecyclerView projectRV;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private List<Project> projectList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_projects);
        this.service = ServiceGenerator.getInstance().getService();

        projectRV = findViewById(R.id.rvProject);
        progressBar = findViewById(R.id.pbLoading);

        Call<List<Project>> call = service.getAllProjects();
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                progressBar.setVisibility(View.GONE);

                int code = response.code();
                Log.d("[d]", "response status code: " + code);

                if (response != null && code == 200) {
                    if(projectList != null && projectList.size() >0 ) {
                        for (int i = 0; i < projectList.size(); i++) {

                            recyclerViewAdapter = new RecyclerViewAdapter(ShowAllProjectsActivity.this, projectList);
                            linearLayoutManager = new LinearLayoutManager(ShowAllProjectsActivity.this);
                            projectRV.setLayoutManager(linearLayoutManager);
                            projectRV.setAdapter(recyclerViewAdapter);
                        }
                    }
                }else
                {
                    Log.d("[d]", "NO PROJECT FOUND!");
                    Toast.makeText(getApplicationContext(), "NO PROJECT FOUND", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.e("[e]", "Error: " + t.toString());
                Toast.makeText(ShowAllProjectsActivity.this, "ERROR OCCURRED!", Toast.LENGTH_LONG).show();
            }
        });
    }
}

     */

