package com.project.appcv.View.Edit;

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

import com.project.appcv.APIService.APIService;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.ContinueActivity;
import com.project.appcv.View.ForgotPasswordActivity;
import com.project.appcv.View.SignupActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordActivity extends AppCompatActivity {
    EditText editTextCurrentPassword,editTextPassword,editTextConfirmPassword;
    Button buttonChangePassword,btnForgotPassword;
    TextView textViewErrorConfirm,textViewErrorCurrentPassword,textViewErrorPassword;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        editTextCurrentPassword=findViewById(R.id.editTextCurrentPassword);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        textViewErrorConfirm=findViewById(R.id.tvErrorConfirm);
        textViewErrorCurrentPassword=findViewById(R.id.tvErrorCurrentPassword);
        textViewErrorPassword=findViewById(R.id.tvErrorPassword);
        btnForgotPassword=findViewById(R.id.buttonForgotPassword);
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
        buttonChangePassword=findViewById(R.id.buttonChangePassword);
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                String password=editTextPassword.getText().toString();
                apiService.changePassword("Bearer "+ jwtToken,password).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            finish();
                        } else {
                            // Xử lý lỗi đăng nhập
                            Toast.makeText(EditPasswordActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditPasswordActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}