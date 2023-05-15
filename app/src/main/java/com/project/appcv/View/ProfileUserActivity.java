package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.Model.ProfileUser;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditBirthdayActivity;
import com.project.appcv.View.Edit.EditGenderActivity;
import com.project.appcv.View.Edit.EditImageActivity;
import com.project.appcv.View.Edit.EditProfileCandidateActivity;
import com.project.appcv.View.Edit.EditProfileUserActivity;
import com.project.appcv.View.Edit.EditPasswordActivity;
import com.project.appcv.View.EditJob.EditProfileActivity;
import com.project.appcv.View.EditJob.EditProfileCompanyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUserActivity extends AppCompatActivity {
    ImageButton imageButtonAvatar,btnEditName,btnEditPassword,btnEditEmail,btnEditPhone,btnEditWebsite,
    btnEditBirthday, btnEditGender, btnEditHobby, btnEditCertificate, btnEditIntroduce,btnEditUsername;
    TextView textViewName, textViewUsername,textViewPassword,textViewEmail, textViewPhone, textViewWebsite,
    textViewBirthday, textViewGender, textViewHobby, textViewCertificate, textViewIntroduce,
    textViewBirthdayHeader,textViewGenderHeader,textViewHobbyHeader,textViewCertificateHeader,
    textViewInforCandidateHeader,textViewID;
    Button
    btnLogout,btnCamera;

    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        imageButtonAvatar=findViewById(R.id.imagePUAvatar);
        textViewName=findViewById(R.id.tvPUName);
        textViewUsername=findViewById(R.id.tvPUUsername);
        textViewEmail=findViewById(R.id.tvPUEmail);
        textViewPhone=findViewById(R.id.tvPUPhone);
        textViewWebsite=findViewById(R.id.tvPUWebsite);
        textViewBirthday=findViewById(R.id.tvPUBirthday);
        textViewGender=findViewById(R.id.tvPUGender);
        textViewHobby=findViewById(R.id.tvPUHobby);
        textViewCertificate=findViewById(R.id.tvPUCertificate);
        textViewIntroduce=findViewById(R.id.tvPUIntroduce);
        textViewBirthdayHeader=findViewById(R.id.tvPUBirthdayHeader);
        textViewGenderHeader=findViewById(R.id.tvPUGenderHeader);
        textViewHobbyHeader=findViewById(R.id.tvPUHobbyHeader);
        textViewCertificateHeader=findViewById(R.id.tvPUCertificateHeader);
        textViewInforCandidateHeader=findViewById(R.id.tvPUInforCandidate);
        btnEditUsername=findViewById(R.id.btnPUUsername);
        textViewID=findViewById(R.id.tvPUId);
        EditUser();
        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();
        if (role.equals("candidate"))
            GetInforUserCandidate();
        else if (role.equals("company")) {
            GetInforUserCompany();
        }
    }

    private void GetInforUserCandidate(){
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<ProfileUser> call=apiService.getInforUser("Bearer " + jwtToken);
        call.enqueue(new Callback<ProfileUser>() {
            @Override
            public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                if (response.isSuccessful()) {
                    ProfileUser user = response.body();
                    // Xử lý thông tin user
                    textViewID.setText("Mã ứng viên: "+user.getId());
                    textViewName.setText(user.getFname());
                    Glide.with(getApplicationContext()).load(user.getImage()).into(imageButtonAvatar);
                    textViewUsername.setText(user.getUsername());
                    textViewEmail.setText(user.getEmail());
                    textViewPhone.setText(user.getPhone());
                    textViewWebsite.setText(user.getWebsite());
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<ProfileUser> call, Throwable t) {

            }
        });
        Call<ProfileCandidate> callCandidate=apiService.getInforCadidate("Bearer " + jwtToken);
        callCandidate.enqueue(new Callback<ProfileCandidate>() {
            @Override
            public void onResponse(Call<ProfileCandidate> call, Response<ProfileCandidate> response) {
                Log.d("Kiretrspon",response.toString());
                if (response.isSuccessful()) {
                    ProfileCandidate user = response.body();
                    // Xử lý thông tin user
                    textViewBirthday.setText(user.getFormattedDate());
                    textViewGender.setText(user.getGender());
                    textViewHobby.setText(user.getHobby());
                    textViewCertificate.setText(user.getCertificate());
                    textViewIntroduce.setText(user.getIntroduce());
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<ProfileCandidate> call, Throwable t) {

            }
        });
    }
    private void GetInforUserCompany(){
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<ProfileUser> call=apiService.getInforUserCompany("Bearer " + jwtToken);
        call.enqueue(new Callback<ProfileUser>() {
            @Override
            public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                if (response.isSuccessful()) {
                    ProfileUser user = response.body();
                    // Xử lý thông tin user
                    textViewID.setText("Mã người dùng: "+user.getId());
                    textViewName.setText(user.getFname());
                    Glide.with(getApplicationContext()).load(user.getImage()).into(imageButtonAvatar);
                    textViewUsername.setText(user.getUsername());
                    btnEditUsername.setEnabled(false);
                    textViewEmail.setText(user.getEmail());
                    textViewPhone.setText(user.getPhone());
                    textViewWebsite.setText(user.getWebsite());
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<ProfileUser> call, Throwable t) {

            }
        });
        Call<Company> callCompany=apiService.getInforCompany("Bearer " + jwtToken);
        callCompany.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                if (response.isSuccessful()) {
                    Company user = response.body();
                    // Xử lý thông tin user
                    textViewInforCandidateHeader.setText("Thông tin công ty");
                    textViewBirthdayHeader.setText("Ngày thành lập");
                    textViewBirthday.setText(user.getFormattedDate());
                    textViewGenderHeader.setText("Lĩnh vực");
                    textViewGender.setText(user.getField());
                    textViewHobbyHeader.setText("Số lượng công việc ");
                    textViewHobby.setText(user.getInventoryJob()+"");
                    btnEditHobby.setEnabled(false);
                    textViewCertificateHeader.setText("Mã công ty");
                    textViewCertificate.setText(user.getId()+"");
                    btnEditCertificate.setEnabled(false);
                    textViewIntroduce.setText(user.getInformation());
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {

            }
        });
    }
    private void EditUser(){
        btnEditName=findViewById(R.id.btnPUName);
        btnEditPassword=findViewById(R.id.btnPUPassword);
        btnEditEmail=findViewById(R.id.btnPUEmail);
        btnEditPhone=findViewById(R.id.btnPUPhone);
        btnEditWebsite=findViewById(R.id.btnPUWebsite);
        btnEditBirthday=findViewById(R.id.btnPUBirthday);
        btnEditGender=findViewById(R.id.btnPUGender);
        btnEditHobby=findViewById(R.id.btnPUHobby);
        btnEditCertificate=findViewById(R.id.btnPUCertificate);
        btnEditIntroduce=findViewById(R.id.btnPUIntroduce);
        btnCamera=findViewById(R.id.btnPUCamera);

        String role= SharedPrefManager.getInstance(getApplicationContext()).getRole();
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditImageActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnLogout=findViewById(R.id.btnPULogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getApplicationContext()).clear();
                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
        btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileUserActivity.class);
                    String name = textViewName.getText().toString();
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                } else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileActivity.class);
                    String name = textViewName.getText().toString();
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditPasswordActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileUserActivity.class);
                    String email = textViewEmail.getText().toString();
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                }else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileActivity.class);
                    String email = textViewEmail.getText().toString();
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileUserActivity.class);
                    String phone = textViewPhone.getText().toString();
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                } else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileActivity.class);
                    String phone = textViewPhone.getText().toString();
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileUserActivity.class);
                    String website = textViewWebsite.getText().toString();
                    intent.putExtra("website", website);
                    startActivity(intent);
                    finish();
                } else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileActivity.class);
                    String website = textViewWebsite.getText().toString();
                    intent.putExtra("website", website);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditBirthdayActivity.class);
                    String birthday = textViewBirthday.getText().toString();
                    intent.putExtra("birthday", birthday);
                    startActivity(intent);
                    finish();
                }else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditBirthdayActivity.class);
                    String foundedAt = textViewBirthday.getText().toString();
                    intent.putExtra("foundedAt", foundedAt);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditGenderActivity.class);
                    String gender = textViewGender.getText().toString();
                    intent.putExtra("gender", gender);
                    startActivity(intent);
                    finish();
                }else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileCompanyActivity.class);
                    String field = textViewGender.getText().toString();
                    intent.putExtra("field", field);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileCandidateActivity.class);
                    String hobby = textViewHobby.getText().toString();
                    intent.putExtra("hobby", hobby);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileCandidateActivity.class);
                    String certificate = textViewCertificate.getText().toString();
                    intent.putExtra("certificate", certificate);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnEditIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileCandidateActivity.class);
                    String introduce = textViewIntroduce.getText().toString();
                    intent.putExtra("introduce", introduce);
                    startActivity(intent);
                    finish();
                }else if (role.equals("company")) {
                    Intent intent = new Intent(ProfileUserActivity.this, EditProfileCompanyActivity.class);
                    String information = textViewIntroduce.getText().toString();
                    intent.putExtra("information", information);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}