package com.project.appcv.APIService;

import com.google.gson.JsonObject;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.DTO.JWTTokenDto;
import com.project.appcv.DTO.ProfileUserDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.Model.ProfileUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("/api/appcv/top6/company")
    Call<List<Company>> getCompanyAll();
    @GET("/api/appcv/top6/recruit")
    Call<List<Job>> getJobAll();
    @POST("/api/auth/login")
    Call<JWTTokenDto> login(@Body JsonObject body);
    @GET("/api/appcv/address/company/{address_id}")
    Call<List<AddressWorkDto>> getAddressForCompany(@Path("address_id") int address_id);
    @GET("/api/appcv/recruit/company/{company_id}")
    Call<List<Job>> getJobForCompany(@Path("company_id") int company_id);
    @GET("/api/appcv/company/{id}")
    Call<Company> getCompanyById(@Path("id") int id);
    @GET("/api/candidate/user")
    Call<UserPref> getUser(@Header("Authorization") String token);
    @GET("/api/candidate/all/cv")
    Call<ProfileUserDto> getProfileUser(@Header("Authorization") String token);
    @GET("/api/candidate/all/cv")
    Call<Cv> getCvUser(@Header("Authorization") String token);
    @GET("/api/candidate/all/address")
    Call<List<AddressWorkDto>> getAddressUser(@Header("Authorization") String token);
    @GET("/api/candidate/profile")
    Call<ProfileUser> getInforUser(@Header("Authorization") String token);
    @GET("/api/candidate/information")
    Call<ProfileCandidate> getInforCadidate(@Header("Authorization") String token);


}
