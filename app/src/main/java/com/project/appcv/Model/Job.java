package com.project.appcv.Model;

import com.google.gson.annotations.SerializedName;
import com.project.appcv.DTO.AddressWorkDto;

import java.io.Serializable;

public class Job implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("company")
    private Company company;
    private String salary;
    private String position;
    private AddressWorkDto address;
    private String experience;
    private long countdown;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public AddressWorkDto getAddress() {
        return address;
    }

    public void setAddress(AddressWorkDto address) {
        this.address = address;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public long getCountdown() {
        return countdown;
    }

    public void setCountdown(long countdown) {
        this.countdown = countdown;
    }
}
