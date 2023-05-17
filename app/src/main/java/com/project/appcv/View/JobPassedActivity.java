package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;

import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.JobCTAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobPassedActivity extends AppCompatActivity {
    RecyclerView rcJob;
    APIService apiService;
    List<Job> jobList;
    JobCTAdapter jobAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_passed);
        rcJob=findViewById(R.id.rc_alljobapproved);
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        String cv_id = (String) getIntent().getStringExtra("cv_id");
        int id = Integer.parseInt(cv_id);
        if (SharedPrefManager.getInstance(getApplicationContext()).getRole()!=null) {
            apiService = RetrofitClient.getRetrofit().create(APIService.class);
            apiService.getAllJobPassed("Bearer " + jwtToken, id).enqueue(new Callback<List<Job>>() {
                @Override
                public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                    if (response.isSuccessful()) {
                        jobList = response.body();
                        jobAdapter = new JobCTAdapter(jobList, JobPassedActivity.this);
                        rcJob.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                        rcJob.setLayoutManager(layoutManager);
                        rcJob.addItemDecoration(new ItemSpacingDecoration(50));
                        rcJob.setAdapter(jobAdapter);
                        jobAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<Job>> call, Throwable t) {
                    Log.d("logg", t.getMessage());
                }
            });

        }
    }
}