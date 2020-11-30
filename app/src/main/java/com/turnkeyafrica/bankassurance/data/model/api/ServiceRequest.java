package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ServiceRequest {


    @Expose
    @SerializedName("code")
    private BigDecimal code;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("dateSubmitted")
    private String dateSubmitted;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("subject")
    private String subject;

    @Expose
    @SerializedName("comment")
    private String comment;

    @Expose
    @SerializedName("subRefNumber")
    private String subRefNumber;

    @Expose
    @SerializedName("refNumber")
    private String refNumber;

    public String getSubRefNumber() {
        return subRefNumber;
    }

    public void setSubRefNumber(String subRefNumber) {
        this.subRefNumber = subRefNumber;
    }

    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }


    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }
}
