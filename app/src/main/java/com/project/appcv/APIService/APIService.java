package com.project.appcv.APIService;

import com.google.gson.JsonObject;
import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.DTO.CompanyDto;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @POST("/api/auth/login")
    Call<JWTTokenDto> login(@Body JsonObject body);
    @POST("/api/auth/register")
    Call<JWTTokenDto> signup(@Body JsonObject body);


    @GET("/api/appcv/top6/company")
    Call<List<Company>> getTop6CompanyAll();
    @GET("/api/appcv/all/company")
    Call<List<Company>> getCompanyAll();
    @GET("/api/appcv/company/{id}")
    Call<Company> getCompanyById(@Path("id") int id);
    @GET("/api/company/information")
    Call<Company> getInforCompany(@Header("Authorization") String token);



    @GET("/api/appcv/top6/recruit")
    Call<List<Job>> getTop6JobAll();
    @GET("/api/appcv/recruit/{id}")
    Call<Job> getJobById(@Path("id") int id);
    @GET("/api/appcv/recruit")
    Call<List<Job>> getJobAll();
    @GET("/api/appcv/recruit/company/{company_id}")
    Call<List<Job>> getJobForCompany(@Path("company_id") int company_id);
    @GET("/api/company/all/recruit")
    Call<List<Job>> getAllJobForCompany(@Header("Authorization") String token);
    @GET("/api/candidate/recruit/apply")
    Call<List<Job>> getAllJobForApply(@Header("Authorization") String token);
    @GET("/api/appcv/job")
    Call<List<Job>> findJob(@Query("search") String search);



    @GET("/api/appcv/address/company/{address_id}")
    Call<List<AddressWorkDto>> getAddressForCompany(@Path("address_id") int address_id);
    @GET("/user/all/address")
    Call<List<AddressWorkDto>> getAddressUser(@Header("Authorization") String token);
    @PUT("/user/update/address/{id}")
    Call<AddressWorkDto> updateAddress(@Header("Authorization") String token,@Path("id") int id,@Body JsonObject body);
    @POST("/user/create/address")
    Call<AddressWorkDto> createAddressUser(@Header("Authorization") String token,@Body JsonObject body);


    @GET("/api/candidate/all/cv")
    Call<Cv> getCvUser(@Header("Authorization") String token);


    @GET("/user/profile")
    Call<UserPref> getUserCandidate(@Header("Authorization") String token);
    @GET("/api/candidate/all/cv")
    Call<ProfileUserDto> getProfileUser(@Header("Authorization") String token);
    @GET("/api/candidate/profile")
    Call<ProfileUser> getInforUser(@Header("Authorization") String token);
    @GET("/api/candidate/information")
    Call<ProfileCandidate> getInforCadidate(@Header("Authorization") String token);
    @GET("/api/company/profile")
    Call<ProfileUser> getInforUserCompany(@Header("Authorization") String token);



    @POST("/api/candidate/update/profile")
    Call<ProfileUser> updateProfileUser(@Header("Authorization") String token,@Body JsonObject body);
    @POST("/api/candidate/update/candidate")
    Call<ProfileCandidate> updateProfileUserCandidate(@Header("Authorization") String token,@Body JsonObject body);
    @PUT("/api/candidate/update/cv/{id}")
    Call<Cv> updateCvCandidate(@Header("Authorization") String token,@Path("id") int id,@Body JsonObject body);
    @POST("/api/candidate/create/cv")
    Call<Cv> createCvForCandidate(@Header("Authorization") String token,@Body JsonObject body);



    @POST("/api/company/update/profile")
    Call<ProfileUser> updateProfileUserC(@Header("Authorization") String token,@Body JsonObject body);
    @POST("/api/company/update/company")
    Call<Company> updateProfileUserCompany(@Header("Authorization") String token,@Body JsonObject body);
    @PUT("/api/company/update/recruit/{id}")
    Call<Job> updateJobCompany(@Header("Authorization") String token,@Path("id") int id,@Body JsonObject body);
    @POST("/api/company/create/recruit")
    Call<Job> createJobForCompany(@Header("Authorization") String token,@Body JsonObject body);


    @GET("/api/company/cv/apply/recruit/{id}")
    Call<List<Cv>> getAllCvForApply(@Header("Authorization") String token,@Path("id") int id);
    @GET("/api/company/apply/recruit/{id}")
    Call<Integer> countApplyCV(@Header("Authorization") String token,@Path("id") int id);


    @GET("/api/candidate/apply/{id}")
    Call<Job> applyCvToRecruit(@Header("Authorization") String token,@Path("id") int id);


}
