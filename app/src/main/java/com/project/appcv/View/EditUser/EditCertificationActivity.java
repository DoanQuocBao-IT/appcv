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
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditProfileCandidateActivity;
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCertificationActivity extends AppCompatActivity {
    EditText editTextName;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_certification);
        editTextName=findViewById(R.id.editTextCertification);
        buttonSave=findViewById(R.id.btnContinue);
        EditCertificate();
    }
    private void EditCertificate(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                String certificate=editTextName.getText().toString();
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("certificate", certificate);
                Call<ProfileCandidate> call= apiService.updateProfileUserCandidate("Bearer " + jwtToken,jsonObject);
                call.enqueue(new Callback<ProfileCandidate>() {
                    @Override
                    public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                        if (response.isSuccessful()){
                            Intent intent=new Intent(EditCertificationActivity.this, EditIntroActivity.class);
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
}