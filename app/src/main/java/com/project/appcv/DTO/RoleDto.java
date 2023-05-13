package com.project.appcv.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RoleDto implements Serializable {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
