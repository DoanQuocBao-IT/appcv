package com.project.appcv.View.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileUserActivity extends AppCompatActivity {
    EditText editTextName;
    TextView textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);
        editTextName=findViewById(R.id.editTextProfileUser);
        textViewEditHeader=findViewById(R.id.tvEditHeader);
        buttonSave=findViewById(R.id.btnSaveEdit);

        String name = (String) getIntent().getStringExtra("name");
        EditName(name);
        String email = (String) getIntent().getStringExtra("email");
        EditEmail(email);
        String phone = (String) getIntent().getStringExtra("phone");
        EditPhone(phone);
        String website = (String) getIntent().getStringExtra("website");
        EditWebsite(website);

    }
    private void EditName(String name){
        if (name!=null) {
            editTextName.setText(name);
            textViewEditHeader.setText("Sửa tên");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String fname=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("fname", fname);
                    Call<ProfileUser> call= apiService.updateProfileUser("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileUser>() {
                        @Override
                        public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileUserActivity.this, ProfileUserActivity.class);
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
    }
    private void EditEmail(String email){
        if (email!=null) {
            editTextName.setText(email);
            textViewEditHeader.setText("Sửa email");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String email=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("email", email);
                    Call<ProfileUser> call= apiService.updateProfileUser("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileUser>() {
                        @Override
                        public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileUserActivity.this, ProfileUserActivity.class);
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
    }
    private void EditPhone(String phone){
        if (phone!=null) {
            editTextName.setText(phone);
            textViewEditHeader.setText("Sửa số điện thoại");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String phone=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("phone", phone);
                    Call<ProfileUser> call= apiService.updateProfileUser("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileUser>() {
                        @Override
                        public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileUserActivity.this, ProfileUserActivity.class);
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
    }
    private void EditWebsite(String website){
        if (website!=null) {
                editTextName.setText(website);
            textViewEditHeader.setText("Sửa địa chỉ website");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String website=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("website", website);
                    Call<ProfileUser> call= apiService.updateProfileUser("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileUser>() {
                        @Override
                        public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileUserActivity.this, ProfileUserActivity.class);
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
    }
}