package com.project.appcv.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressWorkDto implements Serializable{
    @SerializedName("id")
    private int id;
    @SerializedName("city")
    private String city;
    @SerializedName("address")
    private String address;

    protected AddressWorkDto(Parcel in) {
        id = in.readInt();
        city = in.readString();
        address = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
