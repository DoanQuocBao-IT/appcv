package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

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

public class JobActivity extends AppCompatActivity {
    RecyclerView rcJob;
    Button buttonGoUp;
    APIService apiService;
    List<Job>jobList;
    JobCTAdapter jobAdapter;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        buttonGoUp=findViewById(R.id.btnJGoUp);
        scrollView=findViewById(R.id.scrollViewJob);
        buttonGoUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(View.FOCUS_UP);
            }
        });
        rcJob=findViewById(R.id.rc_alljob);
        GetJob();
    }
    private void GetJob(){
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getJobAll().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    jobAdapter = new JobCTAdapter(jobList, JobActivity.this);
                    rcJob.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    rcJob.setLayoutManager(layoutManager);
                    rcJob.addItemDecoration(new ItemSpacingDecoration(50));
                    rcJob.setAdapter(jobAdapter);
                    jobAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}