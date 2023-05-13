package com.project.appcv.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.appcv.APIService.APIService;
import com.project.appcv.R;

public class CreateCvActivity extends AppCompatActivity{
    EditText editTextGoal, editTextStudy, editTextWork, editTextSkill, editTextPrize, editTextCertificate, editTextProfession, editTextPosition, editTextExperience;
    TextView tvCCvAddress;
    Button btnCreateCv,btnAddress;
    APIService apiService;
    private ActivityResultLauncher<Intent> selectAddressLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null && result.getData().getStringExtra("name") != null) {
                    tvCCvAddress.setText(result.getData().getStringExtra("name"));
                    Log.d("Nhan",result.getData().getStringExtra("name"));
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cv);
        editTextGoal=findViewById(R.id.editTextCCvGoals);
        editTextStudy=findViewById(R.id.editTextCCvStudy);
        editTextWork=findViewById(R.id.editTextCCvWork);
        editTextSkill=findViewById(R.id.editTextCCvSkill);
        editTextPrize=findViewById(R.id.editTextCCvPrize);
        editTextCertificate=findViewById(R.id.editTextCCvCertificate);
        editTextProfession=findViewById(R.id.editTextCCvProfession);
        editTextPosition=findViewById(R.id.editTextCCvPosition);
        editTextExperience=findViewById(R.id.editTextCCvExperience);
        btnCreateCv=findViewById(R.id.btnCCvAdd);
        btnAddress=findViewById(R.id.btnCCvAddress);
        tvCCvAddress=findViewById(R.id.tvCCvAddress);



        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCvActivity.this, AddressActivity.class);
                selectAddressLauncher.launch(intent);
            }
        });
        btnCreateCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}