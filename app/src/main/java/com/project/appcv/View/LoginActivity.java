package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.project.appcv.View.Edit.EditPasswordActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword;
    Button buttonLogin,buttonSignUp,btnNoLogin,btnForgotPassword;
    APIService apiService;
    TextView textViewErrorLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPassword=findViewById(R.id.editTextPassword);
        textViewErrorLogin=findViewById(R.id.tvErrorLogin);
        buttonLogin=findViewById(R.id.buttonLogin);
        btnForgotPassword=findViewById(R.id.buttonForgotPassword);
        btnNoLogin=findViewById(R.id.btnNoLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                String username=editTextUsername.getText().toString();
                String password=editTextPassword.getText().toString();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username", username);
                jsonObject.addProperty("password", password);
                Call<JWTTokenDto> call= apiService.login(jsonObject);
                call.enqueue(new Callback<JWTTokenDto>() {
                    @Override
                    public void onResponse(Call<JWTTokenDto> call, Response<JWTTokenDto> response) {
                        if (response.isSuccessful()){
                            String jwtToken = response.body().getJwtToken();
                            SharedPrefManager.getInstance(getApplicationContext()).saveJwtToken(jwtToken);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            // Xử lý lỗi đăng nhập
                            textViewErrorLogin.setText("Tên đăng nhập hoặc mật khẩu không chính xác");
                            textViewErrorLogin.setTextColor(Color.RED);
                        }
                    }

                    @Override
                    public void onFailure(Call<JWTTokenDto> call, Throwable t) {
                        // Xử lý lỗi kết nối
                        Toast.makeText(LoginActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        buttonSignUp=findViewById(R.id.buttonLoginSignup);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}