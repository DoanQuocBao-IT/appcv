package com.project.appcv.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyDto  implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("fname")
    private String fname;
    @SerializedName("image")
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
