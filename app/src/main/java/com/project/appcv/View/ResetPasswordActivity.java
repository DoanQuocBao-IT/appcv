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

import com.project.appcv.APIService.APIService;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.View.Edit.EditPasswordActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText editTextPassword,editTextConfirmPassword;
    Button buttonResetPassword;
    TextView textViewErrorConfirm,textViewErrorPassword;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        String passcode = (String) getIntent().getStringExtra("passcode");
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        textViewErrorConfirm=findViewById(R.id.tvErrorConfirm);
        textViewErrorPassword=findViewById(R.id.tvErrorPassword);
        buttonResetPassword=findViewById(R.id.buttonResetPassword);
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
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                String password=editTextPassword.getText().toString();
                RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), password);
                apiService.resetPassword(passcode,requestBody).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, "Reset mật khẩu thành công !!\nVui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(ResetPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Xử lý lỗi đăng nhập
                            Toast.makeText(ResetPasswordActivity.this, "Reset mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
}