package com.project.appcv.Model;

import com.google.gson.annotations.SerializedName;
import com.project.appcv.DTO.AddressWorkDto;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("company")
    private Company company;
    @SerializedName("salary")

    private String salary;
    @SerializedName("position")

    private String position;
    @SerializedName("inventory")

    private int inventory;
    @SerializedName("gender")

    private String gender;
    @SerializedName("experience")

    private String experience;
    @SerializedName("responsibilities")

    private String responsibilities;
    @SerializedName("qualifications")

    private String qualifications;
    @SerializedName("interests")

    private String interests;
    @SerializedName("address")

    private AddressWorkDto address;
    @SerializedName("toDate")

    private Date toDate;
    @SerializedName("countdown")

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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public AddressWorkDto getAddress() {
        return address;
    }

    public void setAddress(AddressWorkDto address) {
        this.address = address;
    }

    public long getCountdown() {
        return countdown;
    }

    public void setCountdown(long countdown) {
        this.countdown = countdown;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
