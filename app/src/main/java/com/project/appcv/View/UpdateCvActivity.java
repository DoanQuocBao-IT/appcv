package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.appcv.R;
import com.project.appcv.SharedPrefManager;

public class UpdateCvActivity extends AppCompatActivity {
    TextView textViewTitle;
    Button btnCancel, btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cv);
        textViewTitle=findViewById(R.id.tv_title);
        btnCancel=findViewById(R.id.btn_cancel);
        btnContinue=findViewById(R.id.btn_continue);
        if (SharedPrefManager.getInstance(getApplicationContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getApplicationContext()).getRole();
            if (role.equals("candidate")) {
                btnContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UpdateCvActivity.this, CreateCvActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            } else if (role.equals("company")) {
                btnContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UpdateCvActivity.this, CreateJobActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}