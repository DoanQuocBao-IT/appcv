package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.CompanyAdapter;
import com.project.appcv.Adapter.CompanyCTAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Fragment.HomeFragment;
import com.project.appcv.Model.Company;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyFollowActivity extends AppCompatActivity {
    RecyclerView rcCompany;
    List<Company> companyList;
    APIService apiService;
    CompanyCTAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_follow);
        rcCompany=findViewById(R.id.rc_companyfollow);
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();

        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllCompanyFollow("Bearer " + jwtToken).enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if (response.isSuccessful()) {
                    companyList = response.body();
                    companyAdapter = new CompanyCTAdapter(companyList, CompanyFollowActivity.this);
                    rcCompany.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    rcCompany.setLayoutManager(gridLayoutManager);
                    rcCompany.addItemDecoration(new ItemSpacingDecoration(50));
                    rcCompany.setAdapter(companyAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}