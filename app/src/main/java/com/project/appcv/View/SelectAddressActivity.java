package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.AddressListAdapter;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAddressActivity extends AppCompatActivity{
    ListView rcAddress;
    List<AddressWorkDto> addressWorkDtoList;
    APIService apiService;
    AddressListAdapter addressAdapter;
    int id_cv,id_job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        rcAddress=findViewById(R.id.rc_select_address);




        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();
        if (role.equals("candidate")){
            String cv_id = (String) getIntent().getStringExtra("cv_id");
            id_cv=Integer.parseInt(cv_id);
        } else if (role.equals("company")) {
            String job_id = (String) getIntent().getStringExtra("job_id");
            id_job=Integer.parseInt(job_id);
        }
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<List<AddressWorkDto>> call=apiService.getAddressUser("Bearer " + jwtToken);
        call.enqueue(new Callback<List<AddressWorkDto>>() {
            @Override
            public void onResponse(Call<List<AddressWorkDto>> call, Response<List<AddressWorkDto>> response) {
                if (response.isSuccessful()) {
                    addressWorkDtoList = response.body();
                    // Xử lý thông tin address
                    addressAdapter = new AddressListAdapter(SelectAddressActivity.this,R.layout.item_address_company,addressWorkDtoList);
                    rcAddress.setAdapter(addressAdapter);
                    addressAdapter.notifyDataSetChanged();
                    rcAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            AddressWorkDto selectedAddress = (AddressWorkDto) parent.getItemAtPosition(position);

                            JsonObject jsonObject = new JsonObject();
                            jsonObject.addProperty("address", selectedAddress.getId());
                            if (role.equals("company")) {
                                Call<Job> call = apiService.updateJobCompany("Bearer " + jwtToken, id_job, jsonObject);
                                call.enqueue(new Callback<Job>() {
                                    @Override
                                    public void onResponse(Call<Job> call, Response<Job> response) {
                                        if (response.isSuccessful()) {
                                            Intent resultIntent = new Intent(SelectAddressActivity.this, JobCompanyActivity.class);
                                            String id=Integer.toString(id_job);
                                            resultIntent.putExtra("job_id", id);
                                            startActivity(resultIntent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Job> call, Throwable t) {

                                    }
                                });
                            } else if (role.equals("candidate")) {
                                Call<Cv> callCv=apiService.updateCvCandidate("Bearer " + jwtToken, id_cv, jsonObject);
                                callCv.enqueue(new Callback<Cv>() {
                                    @Override
                                    public void onResponse(Call<Cv> call, Response<Cv> response) {
                                        if (response.isSuccessful()) {
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Cv> call, Throwable t) {

                                    }
                                });
                            }
                        }
                    });

                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<List<AddressWorkDto>> call, Throwable t) {

            }
        });

    }

}