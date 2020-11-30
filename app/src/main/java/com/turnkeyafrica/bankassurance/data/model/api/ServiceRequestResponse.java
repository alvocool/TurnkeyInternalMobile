package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class ServiceRequestResponse {

    @Expose
    @SerializedName("clientCode")
    private String clientCode;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("documents")
    private List<ServiceRequestDocuments> documents;

    @Expose
    @SerializedName("refNumber")
    private String refNumber;

    @Expose
    @SerializedName("requestCategory")
    private BigDecimal requestCategory;

    @Expose
    @SerializedName("source")
    private String source;

    @Expose
    @SerializedName("subRefNumber")
    private String subRefNumber;

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ServiceRequestDocuments> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ServiceRequestDocuments> documents) {
        this.documents = documents;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public BigDecimal getRequestCategory() {
        return requestCategory;
    }

    public void setRequestCategory(BigDecimal requestCategory) {
        this.requestCategory = requestCategory;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubRefNumber() {
        return subRefNumber;
    }

    public void setSubRefNumber(String subRefNumber) {
        this.subRefNumber = subRefNumber;
    }
}


