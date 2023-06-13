package com.project.appcv.View.EditUser;

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
import com.project.appcv.Model.Company;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.EditJob.EditProfileCompanyActivity;
import com.project.appcv.View.ProfileUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFieldActivity extends AppCompatActivity {
    EditText editTextName;
    Button buttonSave;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);
        editTextName=findViewById(R.id.editTextField);
        buttonSave=findViewById(R.id.btnContinue);
        EditField();
    }
    private void EditField(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                String field=editTextName.getText().toString();
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("field", field);
                Call<Company> call= apiService.updateProfileUserCompany("Bearer " + jwtToken,jsonObject);
                call.enqueue(new Callback<Company>() {
                    @Override
                    public void onResponse(Call<Company> call, Response<Company> response) {
                        if (response.isSuccessful()){
                            Intent intent=new Intent(EditFieldActivity.this, EditIntroActivity.class);
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