package com.project.appcv.View.EditJob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.JobCompanyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditJobCompanyActivity extends AppCompatActivity {
    EditText editTextName;
    TextView textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job_company);
        editTextName=findViewById(R.id.editTextJob);
        textViewEditHeader=findViewById(R.id.tvEditJobHeader);
        buttonSave=findViewById(R.id.btnSaveEditJob);
        String job_id = (String) getIntent().getStringExtra("job_id");
        int id=Integer.parseInt(job_id);

        String salary = (String) getIntent().getStringExtra("salary");
        EditSalary(salary,id);
        String position = (String) getIntent().getStringExtra("position");
        EditPosition(position,id);
        String inventory = (String) getIntent().getStringExtra("inventory");
        EditInventory(inventory,id);
        String responsibilities = (String) getIntent().getStringExtra("responsibilities");
        EditResponsibilities(responsibilities,id);
        String qualifications = (String) getIntent().getStringExtra("qualifications");
        EditQualifications(qualifications,id);
        String interests = (String) getIntent().getStringExtra("interests");
        EditInterests(interests,id);

    }
    private void EditSalary(String salary,int job_id){
        if (salary!=null) {
            editTextName.setText(salary);
            textViewEditHeader.setText("Sửa mục mức lương");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String salary=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("salary", salary);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,job_id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditJobCompanyActivity.this,JobCompanyActivity.class);
                                String id=Integer.toString(job_id);
                                intent.putExtra("job_id", id);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditPosition(String position,int job_id){
        if (position!=null) {
            editTextName.setText(position);
            textViewEditHeader.setText("Sửa mục tiêu nghề nghiệp");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String position=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("position", position);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,job_id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditJobCompanyActivity.this,JobCompanyActivity.class);
                                String id=Integer.toString(job_id);
                                intent.putExtra("job_id", id);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditInventory(String inventory,int job_id){
        if (inventory!=null) {
            editTextName.setText(inventory);
            textViewEditHeader.setText("Sửa mục tiêu nghề nghiệp");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String inventory=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("inventory", inventory);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,job_id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditJobCompanyActivity.this,JobCompanyActivity.class);
                                String id=Integer.toString(job_id);
                                intent.putExtra("job_id", id);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditResponsibilities(String responsibilities,int job_id){
        if (responsibilities!=null) {
            editTextName.setText(responsibilities);
            textViewEditHeader.setText("Sửa mô tả công việc");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String responsibilities=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("responsibilities", responsibilities);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,job_id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditJobCompanyActivity.this,JobCompanyActivity.class);
                                String id=Integer.toString(job_id);
                                intent.putExtra("job_id", id);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditQualifications(String qualifications,int job_id){
        if (qualifications!=null) {
            editTextName.setText(qualifications);
            textViewEditHeader.setText("Sửa yêu cầu công việc");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String qualifications=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("qualifications", qualifications);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,job_id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditJobCompanyActivity.this,JobCompanyActivity.class);
                                String id=Integer.toString(job_id);
                                intent.putExtra("job_id", id);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditInterests(String interests,int job_id){
        if (interests!=null) {
            editTextName.setText(interests);
            textViewEditHeader.setText("Sửa quyền lợi");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String interests=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("interests", interests);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,job_id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditJobCompanyActivity.this,JobCompanyActivity.class);
                                String id=Integer.toString(job_id);
                                intent.putExtra("job_id", id);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

}