package com.project.appcv.Model;

import com.google.gson.annotations.SerializedName;
import com.project.appcv.DTO.AddressWorkDto;

import java.io.Serializable;

public class Cv implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("candidates")
    private ProfileCandidate candidates;

    @SerializedName("goals")
    private String goals;
    @SerializedName("study")
    private String study;
    @SerializedName("work")
    private String work;
    @SerializedName("skill")
    private String skill;
    @SerializedName("prize")
    private String prize;
    @SerializedName("certificate")
    private String certificate;
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

    public ProfileCandidate getCandidates() {
        return candidates;
    }

    public void setCandidates(ProfileCandidate candidates) {
        this.candidates = candidates;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
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
