package com.project.appcv.Model;

import com.google.gson.annotations.SerializedName;
import com.project.appcv.DTO.CompanyDto;

import java.io.Serializable;

public class Company  implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("company")
    private CompanyDto company;
    @SerializedName("field")
    private String field;
    @SerializedName("information")
    private String information;
    @SerializedName("inventoryJob")
    private int inventoryJob;
    @SerializedName("formattedDate")
    private String formattedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getInventoryJob() {
        return inventoryJob;
    }

    public void setInventoryJob(int inventoryJob) {
        this.inventoryJob = inventoryJob;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
