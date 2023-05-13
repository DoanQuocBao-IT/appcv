package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.AddressAdapter;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity{
    RecyclerView rcAddress;
    List<AddressWorkDto> addressWorkDtoList;
    APIService apiService;
    AddressAdapter addressAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        rcAddress=findViewById(R.id.rc_address);
        GetIAddressUser();
    }
    private void GetIAddressUser(){
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<List<AddressWorkDto>> call=apiService.getAddressUser("Bearer " + jwtToken);
        call.enqueue(new Callback<List<AddressWorkDto>>() {
            @Override
            public void onResponse(Call<List<AddressWorkDto>> call, Response<List<AddressWorkDto>> response) {
                if (response.isSuccessful()) {
                    addressWorkDtoList = response.body();
                    // Xử lý thông tin address
                    addressAdapter=new AddressAdapter(addressWorkDtoList,AddressActivity.this);
                    rcAddress.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    rcAddress.setLayoutManager(layoutManager);
                    rcAddress.addItemDecoration(new ItemSpacingDecoration(50));
                    rcAddress.setAdapter(addressAdapter);
                    addressAdapter.notifyDataSetChanged();

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