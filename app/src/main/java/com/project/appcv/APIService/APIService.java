package com.project.appcv.APIService;

import com.google.gson.JsonObject;
import com.project.appcv.DTO.JWTTokenDto;
import com.project.appcv.DTO.ProfileUserDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {
    @GET("/api/appcv/top6/company")
    Call<List<Company>> getCompanyAll();
    @GET("/api/appcv/top6/recruit")
    Call<List<Job>> getJobAll();
    @POST("/api/auth/login")
    Call<JWTTokenDto> login(@Body JsonObject body);
    @GET("/api/candidate/user")
    Call<UserPref> getUser(@Header("Authorization") String token);
    @GET("/api/candidate/all/cv")
    Call<ProfileUserDto> getProfileUser(@Header("Authorization") String token);
    @GET("/api/candidate/all/cv")
    Call<Cv> getCvUser(@Header("Authorization") String token);
}
