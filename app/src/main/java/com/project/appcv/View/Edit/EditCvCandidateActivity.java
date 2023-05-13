package com.project.appcv.View.Edit;

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
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCvCandidateActivity extends AppCompatActivity {
    EditText editTextName;
    TextView textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cv_candidate);
        editTextName=findViewById(R.id.editTextCv);
        textViewEditHeader=findViewById(R.id.tvEditCvHeader);
        buttonSave=findViewById(R.id.btnSaveEditCv);
        String cv_id = (String) getIntent().getStringExtra("cv_id");
        int id=Integer.parseInt(cv_id);

        String goals = (String) getIntent().getStringExtra("goals");
        EditGoals(goals,id);
        String study = (String) getIntent().getStringExtra("study");
        EditStudy(study,id);
        String work = (String) getIntent().getStringExtra("work");
        EditWork(work,id);
        String skill = (String) getIntent().getStringExtra("skill");
        EditSkill(skill,id);
        String prize = (String) getIntent().getStringExtra("prize");
        EditPrize(prize,id);
        String certificate = (String) getIntent().getStringExtra("certificate");
        EditCertificate(certificate,id);
        String profession = (String) getIntent().getStringExtra("profession");
        EditProfession(profession,id);
        String position = (String) getIntent().getStringExtra("position");
        EditPosition(position,id);
        String experience = (String) getIntent().getStringExtra("experience");
        EditExperience(experience,id);

    }
    private void EditGoals(String goals,int cv_id){
        if (goals!=null) {
            editTextName.setText(goals);
            textViewEditHeader.setText("Sửa mục tiêu nghề nghiệp");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String goals=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("goals", goals);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditStudy(String study, int cv_id){
        if (study!=null) {
            editTextName.setText(study);
            textViewEditHeader.setText("Sửa học vấn");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String study=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("study", study);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditWork(String work, int cv_id){
        if (work!=null) {
            editTextName.setText(work);
            textViewEditHeader.setText("Sửa kinh nghiệm làm việc");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String work=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("work", work);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditSkill(String skill, int cv_id){
        if (skill!=null) {
            editTextName.setText(skill);
            textViewEditHeader.setText("Sửa kĩ năng");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String skill=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("skill", skill);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditPrize(String prize, int cv_id){
        if (prize!=null) {
            editTextName.setText(prize);
            textViewEditHeader.setText("Sửa giải thưởng");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String prize=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("prize", prize);
                    Log.d("Ktr id",prize+"");
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            Log.d("Ktrres",response.toString());
                            if (response.isSuccessful())
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
    private void EditCertificate(String certificate, int cv_id){
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
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditProfession(String profession, int cv_id){
        if (profession!=null) {
            editTextName.setText(profession);
            textViewEditHeader.setText("Sửa chuyên ngành");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String profession=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("profession", profession);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditPosition(String position, int cv_id){
        if (position!=null) {
            editTextName.setText(position);
            textViewEditHeader.setText("Sửa vị trí ứng tuyển ");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String position=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("position", position);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
    private void EditExperience(String experience, int cv_id){
        if (experience!=null) {
            editTextName.setText(experience);
            textViewEditHeader.setText("Sửa kinh nghệm ");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String experience=editTextName.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("experience", experience);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,cv_id,jsonObject);
                    call.enqueue(new Callback<Cv>() {
                        @Override
                        public void onResponse(Call<Cv> call, Response<Cv> response) {
                            if (response.isSuccessful()){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cv> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

}