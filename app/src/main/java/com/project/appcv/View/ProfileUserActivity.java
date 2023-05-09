package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.ProfileUserDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.Model.ProfileUser;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.Edit.EditBirthdayActivity;
import com.project.appcv.View.Edit.EditCertificateActivity;
import com.project.appcv.View.Edit.EditEmailActivity;
import com.project.appcv.View.Edit.EditGenderActivity;
import com.project.appcv.View.Edit.EditHobbyActivity;
import com.project.appcv.View.Edit.EditImageActivity;
import com.project.appcv.View.Edit.EditIntroduceActivity;
import com.project.appcv.View.Edit.EditNameActivity;
import com.project.appcv.View.Edit.EditPasswordActivity;
import com.project.appcv.View.Edit.EditPhoneActivity;
import com.project.appcv.View.Edit.EditWebsiteActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUserActivity extends AppCompatActivity {
    ImageButton imageButtonAvatar,btnEditName,btnEditPassword,btnEditEmail,btnEditPhone,btnEditWebsite,
    btnEditBirthday, btnEditGender, btnEditHobby, btnEditCertificate, btnEditIntroduce;
    TextView textViewName, textViewUsername,textViewPassword,textViewEmail, textViewPhone, textViewWebsite,
    textViewBirthday, textViewGender, textViewHobby, textViewCertificate, textViewIntroduce;
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
                Intent intent=new Intent(ProfileUserActivity.this, EditNameActivity.class);
                startActivity(intent);
                recreate();
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
                Intent intent=new Intent(ProfileUserActivity.this, EditEmailActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditPhoneActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditWebsiteActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditBirthdayActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditGenderActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditHobbyActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditCertificateActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        btnEditIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUserActivity.this, EditIntroduceActivity.class);
                startActivity(intent);
                recreate();
            }
        });
        GetInforUserCandidate();
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
}