package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.appcv.APIService.APIService;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText editTextEmail;
    Button buttonSendEmail;
    TextView textViewErrorEmail;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editTextEmail=findViewById(R.id.editTextEmail);
        buttonSendEmail=findViewById(R.id.buttonSendEmail);
        textViewErrorEmail=findViewById(R.id.tvErrorEmail);
        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                String email=editTextEmail.getText().toString();
                RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), email);
                apiService.forgotPassword(requestBody).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(ForgotPasswordActivity.this, PasscodeActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        }else {
                            textViewErrorEmail.setText("Email nhập sai hoặc không tồn tại");
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