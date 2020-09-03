package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class NotificationsResponse {

    @Expose
    @SerializedName("code")
    private BigDecimal code;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("clientCode")
    private BigDecimal clientCode;

    @Expose
    @SerializedName("dateCreated")
    private String dateCreated;

    @Expose
    @SerializedName("dateRead")
    private String dateRead;

    @Expose
    @SerializedName("dateSent")
    private String dateSent;

    @Expose
    @SerializedName("delivered")
    private String delivered;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("mode")
    private String mode;

    @Expose
    @SerializedName("read")
    private String read;

    @Expose
    @SerializedName("refNumber")
    private String refNumber;

    @Expose
    @SerializedName("subject")
    private String subject;

    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    public BigDecimal getClientCode() {
        return clientCode;
    }

    public void setClientCode(BigDecimal clientCode) {
        this.clientCode = clientCode;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateRead() {
        return dateRead;
    }

    public void setDateRead(String dateRead) {
        this.dateRead = dateRead;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
