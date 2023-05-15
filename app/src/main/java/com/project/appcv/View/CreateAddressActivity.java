package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.EditJob.EditAddressActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAddressActivity extends AppCompatActivity {
    EditText editTextCity,editTextAddress;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_address);
        editTextCity=findViewById(R.id.editTextACity);
        editTextAddress=findViewById(R.id.editTextAAddress);
        buttonSave=findViewById(R.id.btnSaveEditAAddress);
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
                Call<AddressWorkDto> call=apiService.createAddressUser("Bearer " + jwtToken, jsonObject);
                call.enqueue(new Callback<AddressWorkDto>() {
                    @Override
                    public void onResponse(Call<AddressWorkDto> call, Response<AddressWorkDto> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(CreateAddressActivity.this, AddressActivity.class);
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