package com.project.appcv.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileUserDto implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("profession")
    private String profession;
    @SerializedName("position")
    private String position;
    @SerializedName("experience")
    private String experience;
    @SerializedName("address")
    private AddressWorkDto address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
