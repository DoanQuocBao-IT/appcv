package com.project.appcv.View.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.AddressActivity;
import com.project.appcv.View.EditJob.EditAddressActivity;
import com.project.appcv.View.EditJob.EditProfileCompanyActivity;
import com.project.appcv.View.ProfileUserActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBirthdayActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView editTextBirthday,textViewEditHeader;
    Button buttonSave,buttonSelect;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_birthday);
        editTextBirthday=findViewById(R.id.editTextBirthday);
        textViewEditHeader=findViewById(R.id.tvEditBirthdayHeader);
        buttonSave=findViewById(R.id.btnSaveEditBirthday);
        buttonSelect=findViewById(R.id.btnSaveSelectDay);
        final Calendar calendar = Calendar.getInstance();
        int year = 2000;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();

        if (role.equals("candidate")) {
            String birthday = (String) getIntent().getStringExtra("birthday");
            EditBirthday(birthday);
        } else if (role.equals("company")) {
            String foundedAt = (String) getIntent().getStringExtra("foundedAt");
            EditFoundedAt(foundedAt);
        }
    }
    private void EditBirthday(String birthday){
        if (birthday!=null) {
            editTextBirthday.setText(birthday);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String birthday=editTextBirthday.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("birthday", birthday);
                    Call<ProfileCandidate> call= apiService.updateProfileUserCandidate("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<ProfileCandidate>() {
                        @Override
                        public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditBirthdayActivity.this, ProfileUserActivity.class);
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
    private void EditFoundedAt(String birthday){
        if (birthday!=null) {
            editTextBirthday.setText(birthday);
            textViewEditHeader.setText("Sửa ngày thành lập ");
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                    String foundedAt=editTextBirthday.getText().toString();
                    apiService= RetrofitClient.getRetrofit().create(APIService.class);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("foundedAt", foundedAt);
                    Call<Company> call= apiService.updateProfileUserCompany("Bearer " + jwtToken,jsonObject);
                    call.enqueue(new Callback<Company>() {
                        @Override
                        public void onResponse(Call<Company> call, Response<Company> response) {
                            if (response.isSuccessful()){
                                Intent intent=new Intent(EditBirthdayActivity.this, ProfileUserActivity.class);
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
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String sDay,sMonth;
        if (dayOfMonth<10)
            sDay="0"+dayOfMonth;
        else sDay=dayOfMonth+"";
        if (month<10)
            sMonth="0"+month;
        else sMonth=month+"";

        editTextBirthday.setText( year+"-" + sMonth + "-" +sDay);
    }
}