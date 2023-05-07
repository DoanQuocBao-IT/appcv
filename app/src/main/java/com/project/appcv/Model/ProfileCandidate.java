package com.project.appcv.Model;

import com.google.gson.annotations.SerializedName;

public class ProfileCandidate {
    @SerializedName("id")
    private int id;
    @SerializedName("candidate")
    private ProfileUser candidate;
    @SerializedName("formattedDate")
    private String formattedDate;
    @SerializedName("gender")
    private String gender;
    @SerializedName("introduce")
    private String introduce;
    @SerializedName("hobby")
    private String hobby;
    @SerializedName("certificate")
    private String certificate;
    @SerializedName("age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfileUser getCandidate() {
        return candidate;
    }

    public void setCandidate(ProfileUser candidate) {
        this.candidate = candidate;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
