package com.project.appcv.View.EditUser;

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
import com.project.appcv.Model.ProfileUser;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditProfileUserActivity;
import com.project.appcv.View.EditJob.EditProfileActivity;
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNameActivity extends AppCompatActivity {
    EditText editTextName;
    Button btnComplete,btnCancel;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        editTextName=findViewById(R.id.editTextName);
        btnComplete=findViewById(R.id.btnNComplete);
        btnCancel=findViewById(R.id.btnNCancel);
        if (SharedPrefManager.getInstance(getApplicationContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getApplicationContext()).getRole();

            if (role.equals("candidate")) {
                btnComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String jwtToken = SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                        String fname = editTextName.getText().toString();
                        apiService = RetrofitClient.getRetrofit().create(APIService.class);
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("fname", fname);
                        Call<ProfileUser> call = apiService.updateProfileUser("Bearer " + jwtToken, jsonObject);
                        call.enqueue(new Callback<ProfileUser>() {
                            @Override
                            public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                                Log.d("Kiyfdwe", response.toString());
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(EditNameActivity.this, EditEmailActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ProfileUser> call, Throwable t) {

                            }
                        });
                    }
                });
            } else if (role.equals("company")) {
                btnComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String jwtToken = SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                        String fname = editTextName.getText().toString();
                        apiService = RetrofitClient.getRetrofit().create(APIService.class);
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("fname", fname);
                        Call<ProfileUser> call = apiService.updateProfileUserC("Bearer " + jwtToken, jsonObject);
                        call.enqueue(new Callback<ProfileUser>() {
                            @Override
                            public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(EditNameActivity.this, EditEmailActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ProfileUser> call, Throwable t) {

                            }
                        });
                    }
                });
            }
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditNameActivity.this, EditEmailActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}