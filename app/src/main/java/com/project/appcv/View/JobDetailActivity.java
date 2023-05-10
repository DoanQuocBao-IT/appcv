package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailActivity extends AppCompatActivity {
    TextView textViewNameJob,textViewNameCompany,textViewSalary,textViewInventory,textViewGender,textViewExperience,textViewAddress,
            textViewResponsibility,textQualification,textViewInterest;
    ImageView imageViewAvatar;
    Button btnExpend;
    APIService apiService;
    private boolean state;
    ConstraintLayout constraintLayoutGender,constraintLayoutExperience,constraintLayoutAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        textViewNameJob=findViewById(R.id.tvDJNameJob);
        textViewNameCompany=findViewById(R.id.tvDJNameCompany);
        textViewSalary=findViewById(R.id.tvDJSalary);
        textViewInventory=findViewById(R.id.tvDJInventory);
        textViewGender=findViewById(R.id.tvDJGender);
        textViewExperience=findViewById(R.id.tvDJExperience);
        textViewAddress=findViewById(R.id.tvDJAddress);
        textViewResponsibility=findViewById(R.id.tvDJResponsibilities);
        textQualification=findViewById(R.id.tvDJQualifications);
        textViewInterest=findViewById(R.id.tvDJInterests);
        imageViewAvatar=findViewById(R.id.DJAvatar);
        constraintLayoutGender=findViewById(R.id.constraintDJGender);
        constraintLayoutExperience=findViewById(R.id.constraintDJExperience);
        constraintLayoutAddress=findViewById(R.id.constraintDJAddress);
        btnExpend=findViewById(R.id.btnDJExpend);
        state=true;
        btnExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state)
                {
                    constraintLayoutGender.setVisibility(View.GONE);
                    constraintLayoutExperience.setVisibility(View.GONE);
                    constraintLayoutAddress.setVisibility(View.GONE);
                    state=false;
                }else {
                    constraintLayoutGender.setVisibility(View.VISIBLE);
                    constraintLayoutExperience.setVisibility(View.VISIBLE);
                    constraintLayoutAddress.setVisibility(View.VISIBLE);
                    state=true;
                }
            }
        });
        String job_id = (String) getIntent().getStringExtra("job_id");
        int id=Integer.parseInt(job_id);
        GetJob(id);

    }
    private void GetJob(int job_id){
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<Job> call= apiService.getJobById(job_id);
        call.enqueue(new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                if (response.isSuccessful()){
                    Job job=response.body();
                    textViewNameJob.setText(job.getPosition());
                    Glide.with(getApplicationContext()).load(job.getCompany().getCompany().getImage()).into(imageViewAvatar);
                    textViewNameCompany.setText(job.getCompany().getCompany().getFname());
                    textViewSalary.setText(job.getSalary());
                    textViewInventory.setText(job.getInventory()+"");
                    textViewGender.setText(job.getGender());
                    textViewExperience.setText(job.getExperience());
                    textViewAddress.setText(job.getAddress().getCity()+": "+job.getAddress().getAddress());
                    textViewResponsibility.setText(job.getResponsibilities());
                    textQualification.setText(job.getQualifications());
                    textViewInterest.setText(job.getInterests());

                }
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {

            }
        });
    }
}