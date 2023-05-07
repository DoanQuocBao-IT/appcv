package com.project.appcv.DTO;

import com.google.gson.annotations.SerializedName;

public class JWTTokenDto {
    @SerializedName("jwtToken")
    private String jwtToken;

    public JWTTokenDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
