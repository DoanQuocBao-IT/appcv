package com.project.appcv.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class MessageDto implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("sender")
    private UserPref sender;
    @SerializedName("content")
    private String content;
    @SerializedName("createAt")
    private Date createAt;
    @SerializedName("receiver")
    private UserPref receiver;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserPref getSender() {
        return sender;
    }

    public void setSender(UserPref sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public UserPref getReceiver() {
        return receiver;
    }

    public void setReceiver(UserPref receiver) {
        this.receiver = receiver;
    }
}
