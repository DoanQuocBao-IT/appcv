package com.project.appcv.View.EditUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.appcv.MainActivity;
import com.project.appcv.R;

public class EditInforActivity extends AppCompatActivity {
    Button btnCancel, btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_infor);
        btnCancel=findViewById(R.id.btnNCancel);
        btnContinue=findViewById(R.id.btnNComplete);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditInforActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditInforActivity.this, EditDayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}