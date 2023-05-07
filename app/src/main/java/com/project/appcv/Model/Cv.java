package com.project.appcv.Model;

import com.google.gson.annotations.SerializedName;
import com.project.appcv.DTO.AddressWorkDto;

import java.io.Serializable;

public class Cv implements Serializable {
    @SerializedName("id")
    private int city;
    @SerializedName("candidates")
    private ProfileCandidate candidates;
    @SerializedName("profession")
    private String profession;
    @SerializedName("position")
    private String position;
    @SerializedName("experience")
    private String experience;
    @SerializedName("address")
    private AddressWorkDto address;

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public ProfileCandidate getCandidates() {
        return candidates;
    }

    public void setCandidates(ProfileCandidate candidates) {
        this.candidates = candidates;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public AddressWorkDto getAddress() {
        return address;
    }

    public void setAddress(AddressWorkDto address) {
        this.address = address;
    }
}
