package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class QouteResponce {

    @Expose
    @SerializedName("code")
    private BigDecimal code;
    @Expose
    @SerializedName("clntCode")
    private BigDecimal clntCode;
    @Expose
    @SerializedName("premiumAmount")
    private BigDecimal premiumAmount;
    @Expose
    @SerializedName("quotCode")
    private BigDecimal quotCode;
    @Expose
    @SerializedName("quotNumber")
    private String quotNumber;
    @Expose
    @SerializedName("bindCode")
    private BigDecimal bindCode;
    @Expose
    @SerializedName("agnName")
    private String agnName;
    @Expose
    @SerializedName("bindName")
    private String bindName;
    @Expose
    @SerializedName("consCode")
    private String consCode;
    @Expose
    @SerializedName("gpwDate")
    private String gpwDate;
    @Expose
    @SerializedName("quotSaved")
    private String quotSaved;

    public String getQuotSaved() {
        return quotSaved;
    }

    public void setQuotSaved(String quotSaved) {
        this.quotSaved = quotSaved;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    public void setQuotCode(BigDecimal quotCode) {
        this.quotCode = quotCode;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public void setBindCode(BigDecimal bindCode) {
        this.bindCode = bindCode;
    }

    public void setAgnName(String agnName) {
        this.agnName = agnName;
    }

    public BigDecimal getCode() {
        return code;
    }

    public BigDecimal getClntCode() {
        return clntCode;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public BigDecimal getQuotCode() {
        return quotCode;
    }

    public String getQuotNumber() {
        return quotNumber;
    }

    public BigDecimal getBindCode() {
        return bindCode;
    }

    public String getAgnName() {
        return agnName;
    }

    public String getBindName() {
        return bindName;
    }

    public String getConsCode() {
        return consCode;
    }

    public String getGpwDate() {
        return gpwDate;
    }
}

