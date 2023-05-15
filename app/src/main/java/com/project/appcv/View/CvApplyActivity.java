package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.CvAdapter;
import com.project.appcv.Adapter.JobApplyAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Fragment.NoticeFragment;
import com.project.appcv.Model.Cv;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CvApplyActivity extends AppCompatActivity {
    RecyclerView rc_cvApply;
    List<Cv> cvList;
    CvAdapter cvAdapter;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_apply);
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        String job_id = (String) getIntent().getStringExtra("job_id");
        int id=Integer.parseInt(job_id);
        rc_cvApply=findViewById(R.id.rc_cvApply);
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllCvForApply("Bearer " + jwtToken,id).enqueue(new Callback<List<Cv>>() {
            @Override
            public void onResponse(Call<List<Cv>> call, Response<List<Cv>> response) {
                if (response.isSuccessful()) {
                    cvList = response.body();
                    cvAdapter = new CvAdapter(cvList, CvApplyActivity.this);
                    rc_cvApply.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    rc_cvApply.setLayoutManager(layoutManager);
                    rc_cvApply.addItemDecoration(new ItemSpacingDecoration(50));
                    rc_cvApply.setAdapter(cvAdapter);
                    cvAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Cv>> call, Throwable t) {

            }
        });
    }
}