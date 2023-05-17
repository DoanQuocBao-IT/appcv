package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.JWTTokenDto;
import com.project.appcv.MainActivity;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword,editTextConfirmPassword;
    Button buttonSignUpCandidate,buttonSignUpCompany,btnLogin;
    TextView textViewErrorConfirm,textViewErrorUsername,textViewErrorPassword;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        textViewErrorConfirm=findViewById(R.id.tvErrorConfirm);
        textViewErrorUsername=findViewById(R.id.tvErrorUsername);
        textViewErrorPassword=findViewById(R.id.tvErrorPassword);
        btnLogin=findViewById(R.id.buttonSignupLogin);
        editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textViewErrorConfirm.setText("Nhập lại mật khẩu");
                textViewErrorConfirm.setTextColor(Color.BLACK);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){
                    textViewErrorConfirm.setText("Mật khẩu nhập lại chính xác");
                    textViewErrorConfirm.setTextColor(Color.GREEN);
                }else {
                    textViewErrorConfirm.setText("Mật khẩu nhập lại không chính xác");
                    textViewErrorConfirm.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){
                    textViewErrorConfirm.setText("Mật khẩu nhập lại chính xác");
                    textViewErrorConfirm.setTextColor(Color.GREEN);
                }else {
                    textViewErrorConfirm.setText("Mật khẩu nhập lại không chính xác");
                    textViewErrorConfirm.setTextColor(Color.RED);
                }
            }
        });
        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$";
                if (editTextUsername.getText().toString().matches(pattern)){
                    textViewErrorUsername.setText("Tên đăng nhập hợp lệ");
                    textViewErrorUsername.setTextColor(Color.GREEN);
                }else {
                    textViewErrorUsername.setText("Tên đăng nhập phải có ít 6 kí tự gồm ít nhất 1 chữ cái, 1 chữ số, 1 kí tự ");
                    textViewErrorUsername.setTextColor(Color.RED);
                }
            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonSignUpCandidate=findViewById(R.id.buttonSignupCandidate);
        buttonSignUpCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                String username=editTextUsername.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username", username);
                jsonObject.addProperty("password", password);
                jsonObject.addProperty("roleName", "candidate");
                Call<JWTTokenDto> call = apiService.signup(jsonObject);
                call.enqueue(new Callback<JWTTokenDto>() {
                    @Override
                    public void onResponse(Call<JWTTokenDto> call, Response<JWTTokenDto> response) {
                        if (response.isSuccessful()) {
                            String jwtToken = response.body().getJwtToken();
                            SharedPrefManager.getInstance(getApplicationContext()).saveJwtToken(jwtToken);
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Xử lý lỗi đăng nhập
                            Toast.makeText(SignupActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JWTTokenDto> call, Throwable t) {
                        // Xử lý lỗi kết nối
                        Toast.makeText(SignupActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        buttonSignUpCompany=findViewById(R.id.buttonSignupCompany);
        buttonSignUpCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                String username=editTextUsername.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username", username);
                jsonObject.addProperty("password", password);
                jsonObject.addProperty("roleName", "company");
                Call<JWTTokenDto> call = apiService.signup(jsonObject);
                call.enqueue(new Callback<JWTTokenDto>() {
                    @Override
                    public void onResponse(Call<JWTTokenDto> call, Response<JWTTokenDto> response) {
                        if (response.isSuccessful()) {
                            String jwtToken = response.body().getJwtToken();
                            SharedPrefManager.getInstance(getApplicationContext()).saveJwtToken(jwtToken);
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Xử lý lỗi đăng nhập
                            Toast.makeText(SignupActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JWTTokenDto> call, Throwable t) {
                        // Xử lý lỗi kết nối
                        Toast.makeText(SignupActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}