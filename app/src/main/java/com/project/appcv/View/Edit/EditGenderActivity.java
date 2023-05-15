package com.project.appcv.View.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Job;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.Model.ProfileUser;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.JobCompanyActivity;
import com.project.appcv.View.ProfileUserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditGenderActivity extends AppCompatActivity {
    TextView editTextGender,textViewEditHeader;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gender);
        editTextGender=findViewById(R.id.tvEditGender);
        textViewEditHeader=findViewById(R.id.tvEditGenderHeader);
        buttonSave=findViewById(R.id.btnSaveEditGender);
        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();

        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Other");
        if (role.equals("candidate")) {
            String gender = (String) getIntent().getStringExtra("gender");
            EditGenderCandidate(gender);
        } else if (role.equals("company")) {
            genders.add("No gender require");
            String job_id = (String) getIntent().getStringExtra("job_id");
            int id=Integer.parseInt(job_id);
            String gender = (String) getIntent().getStringExtra("gender");
            EditGenderJobCompany(gender,id);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                editTextGender.setText("" + selectedGender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void EditGenderCandidate(String gender){
        if (gender!=null) {
            editTextGender.setText(gender);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String gendere=editTextGender.getText().toString();
                    String editGender="";
                    if (gendere.equals("Male")){
                        editGender="male";
                    } else if (gendere.equals("Female")) {
                        editGender="female";
                    } else if (gendere.equals("Other")) {
                        editGender="other";
                    } else if (gendere.equals("No gender require")) {
                        editGender="norequire";
                    }
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("gender", editGender);
                    Call<ProfileCandidate> call= apiService.updateProfileUserCandidate("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileCandidate>() {
                        @Override
                        public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditGenderActivity.this, ProfileUserActivity.class);
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
    private void EditGenderJobCompany(String gender,int id){
        if (gender!=null) {
            editTextGender.setText(gender);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String gendere=editTextGender.getText().toString();
                    String editGender="";
                    if (gendere.equals("Male")){
                        editGender="male";
                    } else if (gendere.equals("Female")) {
                        editGender="female";
                    } else if (gendere.equals("Other")) {
                        editGender="other";
                    } else if (gendere.equals("No gender require")) {
                        editGender="norequire";
                    }
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("gender", editGender);
                    Call<Job> call= apiService.updateJobCompany("Bearer " + jwtToken,id,jsonObject);
                    call.enqueue(new Callback<Job>() {
                        @Override
                        public void onResponse(Call<Job> call, Response<Job> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditGenderActivity.this, JobCompanyActivity.class);
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