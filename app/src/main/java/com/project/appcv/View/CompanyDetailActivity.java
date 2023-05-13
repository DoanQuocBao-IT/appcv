package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.AddressAdapter;
import com.project.appcv.Adapter.AddressCompanyAdapter;
import com.project.appcv.Adapter.JobCTAdapter;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailActivity extends AppCompatActivity {

    TextView textViewName,textViewInformation,textViewFoundedAt,textViewField,
    textViewEmail,textViewPhone,textViewWebsite;
    ImageView imageViewAvatar;
    AddressCompanyAdapter addressAdapter;
    JobCTAdapter jobAdapter;
    APIService apiService;
    List<AddressWorkDto> addressWorkDtoList;
    List<Job> jobList;
    RecyclerView rcAddress,rcJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        textViewName=findViewById(R.id.tvDCName);
        textViewInformation=findViewById(R.id.tvDCInformation);
        textViewFoundedAt=findViewById(R.id.tvDCCreateAt);
        textViewField=findViewById(R.id.tvDCField);
        textViewEmail=findViewById(R.id.tvDCEmail);
        textViewPhone=findViewById(R.id.tvDCPhone);
        textViewWebsite=findViewById(R.id.tvDCWebsite);
        imageViewAvatar=findViewById(R.id.imageDCAvatar);
        String company_id = (String) getIntent().getStringExtra("company_id");
        int id=Integer.parseInt(company_id);
        rcAddress=findViewById(R.id.rc_DCAddress);
        rcAddress.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GetCompany(id);
        rcJob=findViewById(R.id.rc_DCjob);
        rcJob.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GetAddress(id);

    ;}
    private void GetAddress(int company_id){
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAddressForCompany(company_id).enqueue(new Callback<List<AddressWorkDto>>() {
            @Override
            public void onResponse(Call<List<AddressWorkDto>> call, Response<List<AddressWorkDto>> response) {
                if (response.isSuccessful()) {
                    addressWorkDtoList = response.body();
                    addressAdapter = new AddressCompanyAdapter(addressWorkDtoList, CompanyDetailActivity.this);
                    rcAddress.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager((getApplicationContext()),LinearLayoutManager.HORIZONTAL,false);
                    rcAddress.setLayoutManager(layoutManager);
                    rcAddress.addItemDecoration(new ItemSpacingDecoration(50));
                    rcAddress.setAdapter(addressAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AddressWorkDto>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
        apiService.getJobForCompany(company_id).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    Log.d("KiemtrdsJob", response.toString());
                    jobAdapter = new JobCTAdapter(jobList, CompanyDetailActivity.this);
                    rcJob.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    rcJob.setLayoutManager(layoutManager);
                    rcJob.addItemDecoration(new ItemSpacingDecoration(50));
                    rcJob.setAdapter(jobAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetCompany(int company_id){
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<Company> call=apiService.getCompanyById(company_id);
        call.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                Log.d("KiemtrdsCompany", response.toString());

                if (response.isSuccessful()){
                    Company company=response.body();
                    textViewName.setText(company.getCompany().getFname());
                    Glide.with(getApplicationContext()).load(company.getCompany().getImage()).into(imageViewAvatar);
                    textViewInformation.setText(company.getInformation());
                    textViewFoundedAt.setText(company.getFormattedDate());
                    textViewField.setText(company.getField());
                    textViewEmail.setText(company.getCompany().getEmail());
                    textViewPhone.setText(company.getCompany().getPhone());
                    textViewWebsite.setText(company.getCompany().getWebsite());
                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                Log.d("loiompany", company_id+" ");

            }
        });
    }
}