package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditBirthdayActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateJobActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText editTextProfession, editTextSalary, editTextPosition, editTextInventory,
            editTextResponsibilities, editTextQualification, editTextInterests;
    TextView tvCJobDeadline;
    Button btnCreateJob,btnSelectDay;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        editTextProfession=findViewById(R.id.editTextCJobProfession);
        editTextSalary=findViewById(R.id.editTextCJobSalary);
        editTextInventory=findViewById(R.id.editTextCJobInventory);
        editTextResponsibilities=findViewById(R.id.editTextCJobResponsibilities);
        editTextQualification=findViewById(R.id.editTextCJobQualifications);
        editTextInterests=findViewById(R.id.editTextCJobInterests);
        editTextPosition=findViewById(R.id.editTextCJobPosition);

        btnCreateJob=findViewById(R.id.btnCJobAdd);
        btnSelectDay=findViewById(R.id.btnCJobSelectDay);
        tvCJobDeadline=findViewById(R.id.tvCJobDeadline);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);

        btnSelectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        btnCreateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("profession", editTextProfession.getText().toString());
                jsonObject.addProperty("salary", editTextSalary.getText().toString());
                jsonObject.addProperty("position", editTextPosition.getText().toString());
                jsonObject.addProperty("inventory", editTextInventory.getText().toString());
                jsonObject.addProperty("responsibilities", editTextResponsibilities.getText().toString());
                jsonObject.addProperty("qualifications", editTextQualification.getText().toString());
                jsonObject.addProperty("interests", editTextInterests.getText().toString());
                jsonObject.addProperty("toDate", tvCJobDeadline.getText().toString());
                Call<Job> call=apiService.createJobForCompany("Bearer " + jwtToken,jsonObject);
                call.enqueue(new Callback<Job>() {
                    @Override
                    public void onResponse(Call<Job> call, Response<Job> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Job> call, Throwable t) {

                    }
                });
            }
        });
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
        tvCJobDeadline.setText( year+"-" + sMonth + "-" +sDay);
    }
}