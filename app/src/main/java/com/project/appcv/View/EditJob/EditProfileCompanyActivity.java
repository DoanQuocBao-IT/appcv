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
import com.project.appcv.Model.Company;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileCompanyActivity extends AppCompatActivity {
    EditText editTextName;
    TextView textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_company);
        editTextName=findViewById(R.id.editTextProfileCompany);
        textViewEditHeader=findViewById(R.id.tvEditCompanyHeader);
        buttonSave=findViewById(R.id.btnSaveEditCompany);

        String foundedAt = (String) getIntent().getStringExtra("foundedAt");
        EditFoundedAt(foundedAt);
        String field = (String) getIntent().getStringExtra("field");
        EditField(field);
        String information = (String) getIntent().getStringExtra("information");
        EditInformation(information);

    }

    private void EditFoundedAt(String birthday){
        if (birthday!=null) {
            editTextName.setText(birthday);
            textViewEditHeader.setText("Ngày thành lập ");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String foundedAt=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("foundedAt", foundedAt);
                    Call<Company> call= apiService.updateProfileUserCompany("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<Company>() {
                        @Override
                        public void onResponse(Call<Company> call, Response<Company> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileCompanyActivity.this, ProfileUserActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<Company> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditField(String field){
        if (field!=null) {
            editTextName.setText(field);
            textViewEditHeader.setText("Sửa lĩnh vực hoạt động");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String field=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("field", field);
                    Call<Company> call= apiService.updateProfileUserCompany("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<Company>() {
                        @Override
                        public void onResponse(Call<Company> call, Response<Company> response) {
                            Log.d("KhtaRe",response.toString());
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileCompanyActivity.this, ProfileUserActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Company> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditInformation(String information){
        if (information!=null) {
            editTextName.setText(information);
            textViewEditHeader.setText("Sửa thông tin");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String information=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("information", information);
                    Call<Company> call= apiService.updateProfileUserCompany("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<Company>() {
                        @Override
                        public void onResponse(Call<Company> call, Response<Company> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileCompanyActivity.this, ProfileUserActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Company> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
}