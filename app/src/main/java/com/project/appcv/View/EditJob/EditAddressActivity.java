package com.project.appcv.View.EditJob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.AddressActivity;
import com.project.appcv.View.JobCompanyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressActivity extends AppCompatActivity {
    EditText editTextCity,editTextAddress;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        editTextCity=findViewById(R.id.editTextCity);
        editTextAddress=findViewById(R.id.editTextAddress);
        buttonSave=findViewById(R.id.btnSaveEditAddress);
        String address_id = (String) getIntent().getStringExtra("address_id");
        int id=Integer.parseInt(address_id);
        String city = (String) getIntent().getStringExtra("city");
        String address = (String) getIntent().getStringExtra("address");
        updateAddress(id,city,address);
    }
    private void updateAddress(int address_id,String city, String address){
        if (city!=null&&address!=null) {
            editTextCity.setText(city);
            editTextAddress.setText(address);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken = SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String city = editTextCity.getText().toString();
                    String address=editTextAddress.getText().toString();
                    apiService = RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("city", city);
                    jsonObject.addProperty("address", address);
                    Call<AddressWorkDto> call = apiService.updateAddress("Bearer " + jwtToken, address_id, jsonObject);
                    call.enqueue(new Callback<AddressWorkDto>() {
                        @Override
                        public void onResponse(Call<AddressWorkDto> call, Response<AddressWorkDto> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(EditAddressActivity.this, AddressActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddressWorkDto> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

}