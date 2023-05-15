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

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCvActivity extends AppCompatActivity{
    EditText editTextGoal, editTextStudy, editTextWork, editTextSkill, editTextPrize, editTextCertificate, editTextProfession, editTextPosition, editTextExperience;
    Button btnCreateCv;
    APIService apiService;


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
        btnCreateCv=findViewById(R.id.btnCCvAdd);
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();

        btnCreateCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("goals", editTextGoal.getText().toString());
                jsonObject.addProperty("study", editTextStudy.getText().toString());
                jsonObject.addProperty("work", editTextWork.getText().toString());
                jsonObject.addProperty("skill", editTextSkill.getText().toString());
                jsonObject.addProperty("prize", editTextPrize.getText().toString());
                jsonObject.addProperty("certificate", editTextCertificate.getText().toString());
                jsonObject.addProperty("profession", editTextProfession.getText().toString());
                jsonObject.addProperty("position", editTextPosition.getText().toString());
                Call<Cv> call=apiService.createCvForCandidate("Bearer " + jwtToken,jsonObject);
                call.enqueue(new Callback<Cv>() {
                    @Override
                    public void onResponse(Call<Cv> call, Response<Cv> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Cv> call, Throwable t) {

                    }
                });
            }
        });
    }

}