package com.project.appcv.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.JobCTAdapter;
import com.project.appcv.Adapter.JobCompanyAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Fragment.CvFragment;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailActivity extends AppCompatActivity {
    TextView textViewNameJob,textViewNameCompany,textViewSalary,textViewInventory,textViewGender,textViewExperience,textViewAddress,
            textViewResponsibility,textQualification,textViewInterest,textViewCompany;
    ImageView imageViewAvatar;
    Button btnExpend,btnApply;
    APIService apiService;
    List<Job> jobList;
    JobCTAdapter jobAdapter;
    RecyclerView rc_job;
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
        textViewCompany=findViewById(R.id.tvDJCompany);
        imageViewAvatar=findViewById(R.id.DJAvatar);
        constraintLayoutGender=findViewById(R.id.constraintDJGender);
        constraintLayoutExperience=findViewById(R.id.constraintDJExperience);
        constraintLayoutAddress=findViewById(R.id.constraintDJAddress);
        rc_job=findViewById(R.id.rc_DJjob);
        rc_job.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        btnExpend=findViewById(R.id.btnDJExpend);
        btnApply=findViewById(R.id.btnDJApply);
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
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        String job_id = (String) getIntent().getStringExtra("job_id");
        int id=Integer.parseInt(job_id);
        if (SharedPrefManager.getInstance(getApplicationContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getApplicationContext()).getRole();
            if (role.equals("company")) {
                btnApply.setVisibility(View.GONE);
            } else if (role.equals("candidate")) {
                btnApply.setVisibility(View.VISIBLE);
                btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        apiService = RetrofitClient.getRetrofit().create(APIService.class);
                        Call<Cv> call=apiService.getCvUser("Bearer "+ jwtToken);
                        call.enqueue(new Callback<Cv>() {
                            @Override
                            public void onResponse(Call<Cv> call, Response<Cv> response) {
                                if (response.isSuccessful()) {
                                    apiService = RetrofitClient.getRetrofit().create(APIService.class);
                                    apiService.applyCvToRecruit("Bearer " + jwtToken, id).enqueue(new Callback<Job>() {
                                        @Override
                                        public void onResponse(Call<Job> call, Response<Job> response) {
                                            if (response.isSuccessful())
                                                showSuccessDialog();
                                        }

                                        @Override
                                        public void onFailure(Call<Job> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(JobDetailActivity.this,"Bạn cần tạo CV trước khi ứng tuyển",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Cv> call, Throwable t) {
                                Toast.makeText(JobDetailActivity.this,"Bạn cần tạo CV trước khi ứng tuyển công việc này",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            } else {
                btnApply.setVisibility(View.VISIBLE);
            }
        }
        else {
            btnApply.setVisibility(View.VISIBLE);
            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(JobDetailActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

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
                    textViewCompany.setText(job.getCompany().getInformation());
                    GetJobForCompany(job.getCompany().getId());
                }
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {

            }
        });
    }
    private void GetJobForCompany(int company_id){
        apiService=RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getJobForCompany(company_id).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    Log.d("KiemtrdsJob", response.toString());
                    jobAdapter = new JobCTAdapter(jobList, JobDetailActivity.this);
                    rc_job.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    rc_job.setLayoutManager(layoutManager);
                    rc_job.addItemDecoration(new ItemSpacingDecoration(50));
                    rc_job.setAdapter(jobAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void showSuccessDialog() {
        // Tạo một AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.success_message, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // Lấy reference tới TextView và Button trong layout
        TextView tvSuccessMessage = view.findViewById(R.id.tv_success_message);
        Button btnOK = view.findViewById(R.id.btn_ok);

        // Đặt message cho TextView
        tvSuccessMessage.setText("Thao tác thành công");

        // Xử lý khi người dùng nhấn OK
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Đóng dialog
            }
        });

        dialog.show(); // Hiển thị dialog
    }
}