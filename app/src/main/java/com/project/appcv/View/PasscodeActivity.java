package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.appcv.R;

public class PasscodeActivity extends AppCompatActivity {
    EditText editTextPasscode;
    Button buttonContinue;
    TextView textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        editTextPasscode=findViewById(R.id.editTextEmail);
        buttonContinue=findViewById(R.id.buttonContinue);
        textViewEmail=findViewById(R.id.EmailNameText);
        String email = (String) getIntent().getStringExtra("email");
        textViewEmail.setText("Please enter the OTP that has just been sent to "+email);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passcode=editTextPasscode.getText().toString();
                Intent intent=new Intent(PasscodeActivity.this, ResetPasswordActivity.class);
                intent.putExtra("passcode",passcode);
                startActivity(intent);
                finish();
            }
        });
    }
}