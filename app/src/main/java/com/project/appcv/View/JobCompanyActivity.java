package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
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
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditBirthdayActivity;
import com.project.appcv.View.Edit.EditGenderActivity;
import com.project.appcv.View.EditJob.EditExperienceActivity;
import com.project.appcv.View.EditJob.EditJobCompanyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobCompanyActivity extends AppCompatActivity {
    TextView textViewNameJob,textViewNameCompany,textViewSalary,textViewInventory,textViewGender,textViewExperience,textViewAddress,
            textViewResponsibility,textQualification,textViewInterest,textViewJobID,textViewCountdown,textViewDeadline;
    ImageView imageViewAvatar;
    Button btnExpend;
    ImageButton btnEditSalary,btnEditInventory,btnEditGender,btnEditExperience,btnEditAddress,btnEditResponsibilities,btnEditQualifications,
            btnEditInterests,btnEditDeadline,btnEditPosition;
    APIService apiService;
    private boolean state;
    ConstraintLayout constraintLayoutGender,constraintLayoutExperience,constraintLayoutAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_company);
        textViewJobID=findViewById(R.id.tvDJCID);
        textViewNameJob=findViewById(R.id.tvDJCNameJob);
        textViewNameCompany=findViewById(R.id.tvDJCNameCompany);
        textViewSalary=findViewById(R.id.tvDJCSalary);
        textViewInventory=findViewById(R.id.tvDJCInventory);
        textViewGender=findViewById(R.id.tvDJCGender);
        textViewExperience=findViewById(R.id.tvDJCExperience);
        textViewAddress=findViewById(R.id.tvDJCAddress);
        textViewResponsibility=findViewById(R.id.tvDJCResponsibilities);
        textQualification=findViewById(R.id.tvDJCQualifications);
        textViewInterest=findViewById(R.id.tvDJCInterests);
        textViewDeadline=findViewById(R.id.tvDJCDeadline);
        textViewCountdown=findViewById(R.id.tvDJCCountdown);
        imageViewAvatar=findViewById(R.id.DJCAvatar);
        constraintLayoutGender=findViewById(R.id.constraintDJCGender);
        constraintLayoutExperience=findViewById(R.id.constraintDJCExperience);
        constraintLayoutAddress=findViewById(R.id.constraintDJCAddress);
        btnExpend=findViewById(R.id.btnDJCExpend);
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
                    textViewJobID.setText(job.getId()+"");
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
                    textViewDeadline.setText("Đến hết ngày "+job.getToDate().toString());
                    textViewCountdown.setText("Còn lại "+job.getCountdown()+" ngày để ứng tuyển");
                    String id=Integer.toString(job_id);
                    EditJob(id);
                    btnEditAddress=findViewById(R.id.btnDJCEditAddress);
                    btnEditAddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(JobCompanyActivity.this, SelectAddressActivity.class);
                            String id=Integer.toString(job_id);
                            intent.putExtra("job_id", id);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {

            }
        });
    }
    private void EditJob(String job_id){
        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();
        btnEditPosition=findViewById(R.id.btnDJCEditPosition);
        btnEditPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("company")) {
                    Intent intent = new Intent(JobCompanyActivity.this, EditJobCompanyActivity.class);
                    intent.putExtra("job_id", job_id);
                    String position = textViewNameJob.getText().toString();
                    intent.putExtra("position", position);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditSalary=findViewById(R.id.btnDJCEditSalary);
        btnEditSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("company")) {
                    Intent intent = new Intent(JobCompanyActivity.this, EditJobCompanyActivity.class);
                    intent.putExtra("job_id", job_id);
                    String salary = textViewSalary.getText().toString();
                    intent.putExtra("salary", salary);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditInventory=findViewById(R.id.btnDJCEditInventory);
        btnEditInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobCompanyActivity.this, EditJobCompanyActivity.class);
                intent.putExtra("job_id", job_id);
                String inventory = textViewInventory.getText().toString();
                intent.putExtra("inventory", inventory);
                startActivity(intent);
                finish();
            }
        });
        btnEditGender=findViewById(R.id.btnDJCEditGender);
        btnEditGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JobCompanyActivity.this, EditGenderActivity.class);
                intent.putExtra("job_id", job_id);
                String gender = textViewGender.getText().toString();
                intent.putExtra("gender", gender);
                startActivity(intent);
                finish();
            }
        });
        btnEditExperience=findViewById(R.id.btnDJCEditExperience);
        btnEditExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JobCompanyActivity.this, EditExperienceActivity.class);
                intent.putExtra("job_id", job_id);
                String experience = textViewExperience.getText().toString();
                intent.putExtra("experience", experience);
                startActivity(intent);
                finish();
            }
        });
        btnEditResponsibilities=findViewById(R.id.btnDJCEditResponsibilities);
        btnEditResponsibilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobCompanyActivity.this, EditJobCompanyActivity.class);
                intent.putExtra("job_id", job_id);
                String responsibilities = textViewResponsibility.getText().toString();
                intent.putExtra("responsibilities", responsibilities);
                startActivity(intent);
                finish();
            }
        });
        btnEditQualifications=findViewById(R.id.btnDJCEditQualifications);
        btnEditQualifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobCompanyActivity.this, EditJobCompanyActivity.class);
                intent.putExtra("job_id", job_id);
                String qualifications = textQualification.getText().toString();
                intent.putExtra("qualifications", qualifications);
                startActivity(intent);
                finish();
            }
        });
        btnEditInterests=findViewById(R.id.btnDJCEditInterests);
        btnEditInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobCompanyActivity.this, EditJobCompanyActivity.class);
                intent.putExtra("job_id", job_id);
                String interests = textViewInterest.getText().toString();
                intent.putExtra("interests", interests);
                startActivity(intent);
                finish();
            }
        });
        btnEditDeadline=findViewById(R.id.btnDJCEditDeadline);
        btnEditDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobCompanyActivity.this, EditBirthdayActivity.class);
                intent.putExtra("job_id", job_id);
                String toDate = textViewDeadline.getText().toString();
                intent.putExtra("toDate", toDate);
                startActivity(intent);
                finish();
            }
        });
    }
}