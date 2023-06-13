package com.project.appcv.View.EditUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.MainActivity;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditProfileCandidateActivity;
import com.project.appcv.View.EditJob.EditProfileCompanyActivity;
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditIntroActivity extends AppCompatActivity {
    EditText editTextName;
    TextView textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intro);
        editTextName=findViewById(R.id.editTextIntroduce);
        textViewEditHeader=findViewById(R.id.tvEditIntroduceHeader);
        buttonSave=findViewById(R.id.btnContinue);
        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();
        if (role.equals("candidate")) {
            EditIntroduce();
        } else if (role.equals("company")) {
            EditInformation();
        }

    }
    private void EditIntroduce(){
        textViewEditHeader.setText("Thêm thông tin giới thiệu");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                String introduce=editTextName.getText().toString();
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("introduce", introduce);
                Call<ProfileCandidate> call= apiService.updateProfileUserCandidate("Bearer " + jwtToken,jsonObject);
                call.enqueue(new Callback<ProfileCandidate>() {
                    @Override
                    public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                        if (response.isSuccessful()){
                            Intent intent=new Intent(EditIntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileCandidate> call, Throwable t) {

                    }
                });
            }
        });
    }
    private void EditInformation(){
        textViewEditHeader.setText("Thêm thông tin công ty");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                String information=editTextName.getText().toString();
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("information", information);
                Call<Company> call= apiService.updateProfileUserCompany("Bearer " + jwtToken,jsonObject);
                call.enqueue(new Callback<Company>() {
                    @Override
                    public void onResponse(Call<Company> call, Response<Company> response) {
                        if (response.isSuccessful()){
                            Intent intent=new Intent(EditIntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Company> call, Throwable t) {

                    }
                });
            }
        });
    }
}