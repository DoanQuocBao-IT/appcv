package com.project.appcv.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class UserPref implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("fname")
    private String fname;
    @SerializedName("image")
    private String image;
    @SerializedName("roles")
    private Set<RoleDto> roles;

    public UserPref(int id, String fname, String image) {
        this.id = id;
        this.fname = fname;
        this.image = image;
    }

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

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
