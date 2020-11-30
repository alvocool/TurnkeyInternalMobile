package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceRequestDocuments {

    public ServiceRequestDocuments(String content, String name) {
        this.content = content;
        this.name = name;
    }

    public ServiceRequestDocuments() {
    }

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("name")
    private String name;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
