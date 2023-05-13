package com.project.appcv.View.Edit;

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
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileCandidateActivity extends AppCompatActivity {
    EditText editTextName;
    TextView textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_candidate);
        editTextName=findViewById(R.id.editTextProfileCandidate);
        textViewEditHeader=findViewById(R.id.tvEditCandidateHeader);
        buttonSave=findViewById(R.id.btnSaveEditCandidate);

        String birthday = (String) getIntent().getStringExtra("birthday");
        EditBirthday(birthday);
        String hobby = (String) getIntent().getStringExtra("hobby");
        EditHobby(hobby);
        String certificate = (String) getIntent().getStringExtra("certificate");
        EditCertificate(certificate);
        String introduce = (String) getIntent().getStringExtra("introduce");
        EditIntroduce(introduce);
    }

    private void EditBirthday(String birthday){
        if (birthday!=null) {
            editTextName.setText(birthday);
            textViewEditHeader.setText("Sửa ngày sinh");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String birthday=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("birthday", birthday);
                    Call<ProfileCandidate> call= apiService.updateProfileUserCandidate("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileCandidate>() {
                        @Override
                        public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileCandidateActivity.this, ProfileUserActivity.class);
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
    private void EditHobby(String hobby){
        if (hobby!=null) {
            editTextName.setText(hobby);
            textViewEditHeader.setText("Sửa sở thích");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String hobby=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("hobby", hobby);
                    Call<ProfileCandidate> call= apiService.updateProfileUserCandidate("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileCandidate>() {
                        @Override
                        public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditProfileCandidateActivity.this, ProfileUserActivity.class);
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
    private void EditCertificate(String certificate){
        if (certificate!=null) {
            editTextName.setText(certificate);
            textViewEditHeader.setText("Sửa chứng chỉ");
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
                                Intent intent=new Intent(EditProfileCandidateActivity.this, ProfileUserActivity.class);
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
    private void EditIntroduce(String introduce){
        if (introduce!=null) {
            editTextName.setText(introduce);
            textViewEditHeader.setText("Sửa địa chỉ website");
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
                                Intent intent=new Intent(EditProfileCandidateActivity.this, ProfileUserActivity.class);
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
}