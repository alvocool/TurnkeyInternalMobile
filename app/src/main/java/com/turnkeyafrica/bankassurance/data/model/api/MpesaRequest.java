package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class MpesaRequest {

    @Expose
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @Expose
    @SerializedName("transactionDesc")
    private String transactionDesc;
    @Expose
    @SerializedName("accountReference")
    private String accountReference;
    @Expose
    @SerializedName("amount")
    private BigDecimal amount;
    @Expose
    @SerializedName("transactionType")
    private String transactionType;
    @Expose
    @SerializedName("senderName")
    private String senderName;
    @Expose
    @SerializedName("agentCode")
    private BigDecimal agentCode;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public BigDecimal getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(BigDecimal agentCode) {
        this.agentCode = agentCode;
    }
}
