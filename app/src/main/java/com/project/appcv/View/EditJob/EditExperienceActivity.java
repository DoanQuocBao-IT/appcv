package com.project.appcv.View.EditJob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditGenderActivity;
import com.project.appcv.View.JobCompanyActivity;
import com.project.appcv.View.ProfileUserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditExperienceActivity extends AppCompatActivity {
    TextView editTextExperience,textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experience);
        editTextExperience=findViewById(R.id.tvEditExperience);
        textViewEditHeader=findViewById(R.id.tvEditExperienceHeader);
        buttonSave=findViewById(R.id.btnSaveEditExperience);
        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();

        List<String> genders = new ArrayList<>();
        genders.add("Going to work");
        genders.add("Less than 1 year");
        genders.add("1 year");
        genders.add("2 year");
        genders.add("3 year");
        genders.add("4 year");
        genders.add("5 year");
        genders.add("Over 5 year");
        if (role.equals("candidate")) {
            String cv_id = (String) getIntent().getStringExtra("cv_id");
            int id=Integer.parseInt(cv_id);
            String experience = (String) getIntent().getStringExtra("experience");
            EditExperienceCandidate(experience,id);
        } else if (role.equals("company")) {
            genders.add("No experience required");
            String job_id = (String) getIntent().getStringExtra("job_id");
            int id=Integer.parseInt(job_id);
            String experience = (String) getIntent().getStringExtra("experience");
            EditExperienceJobCompany(experience,id);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        Spinner genderSpinner = (Spinner) findViewById(R.id.experienceSpinner);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                editTextExperience.setText("" + selectedGender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void EditExperienceCandidate(String experience,int id){
        if (experience!=null) {
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String experiencee=editTextExperience.getText().toString();
                    String editExperience="";
                    if (experiencee.equals("Going to work")){
                        editExperience="goingtowork";
                    } else if (experiencee.equals("Less than 1 year")) {
                        editExperience="lessoneyear";
                    } else if (experiencee.equals("1 year")) {
                        editExperience="oneyear";
                    } else if (experiencee.equals("2 year")) {
                        editExperience="twoyear";
                    } else if (experiencee.equals("3 year")) {
                        editExperience="threeyear";
                    } else if (experiencee.equals("4 year")) {
                        editExperience="fouryear";
                    } else if (experiencee.equals("5 year")) {
                        editExperience="fiveyear";
                    } else if (experiencee.equals("Over 5 year")) {
                        editExperience="overfiveyear";
                    }
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    Log.d("Kitra",editExperience);
                    jsonObject.addProperty("experience", editExperience);
                    Call<Cv> call= apiService.updateCvCandidate("Bearer " + jwtToken,id,jsonObject);
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
    private void EditExperienceJobCompany(String experience,int id){
        if (experience!=null) {
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String experiencee=editTextExperience.getText().toString();
                    String editExperience="";
                    if (experiencee.equals("Going to work")){
                        editExperience="goingtowork";
                    } else if (experiencee.equals("Less than 1 year")) {
                        editExperience="lessoneyear";
                    } else if (experiencee.equals("1 year")) {
                        editExperience="oneyear";
                    } else if (experiencee.equals("2 year")) {
                        editExperience="twoyear";
                    } else if (experiencee.equals("3 year")) {
                        editExperience="threeyear";
                    } else if (experiencee.equals("4 year")) {
                        editExperience="fouryear";
                    } else if (experiencee.equals("5 year")) {
                        editExperience="fiveyear";
                    } else if (experiencee.equals("Over 5 year")) {
                        editExperience="overfiveyear";
                    } else if (experiencee.equals("No experience required")) {
                        editExperience="norequire";
                    }
                    Log.d("Kitra",editExperience);
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("experience", editExperience);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditExperienceActivity.this, JobCompanyActivity.class);
                                String id_job=Integer.toString(id);
                                intent.putExtra("job_id", id_job);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Job> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
}