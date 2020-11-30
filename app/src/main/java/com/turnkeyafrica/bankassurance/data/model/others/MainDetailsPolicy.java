package com.turnkeyafrica.bankassurance.data.model.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class MainDetailsPolicy {

    @Expose
    @SerializedName("amount")
    private String Amount;

    @Expose
    @SerializedName("insurer")
    private String Insurer;

    @Expose
    @SerializedName("bindCode")
    private BigDecimal bindCode;

    @Expose
    @SerializedName("covtShtDesc")
    private String CoverType;

    @Expose
    @SerializedName("startDate")
    private String startDate;

    @Expose
    @SerializedName("renewableDate")
    private String renewableDate;

    @Expose
    @SerializedName("expiredDate")
    private String expiredDate;

    @Expose
    @SerializedName("expired")
    private Boolean expired;

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setCoverType(String coverType) {
        CoverType = coverType;
    }

    public String getCoverType() {
        return CoverType;
    }

    public BigDecimal getBindCode() {
        return bindCode;
    }

    public String getAmount() {
        return Amount;
    }

    public String getInsurer() {
        return Insurer;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getRenewableDate() {
        return renewableDate;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setInsurer(String insurer) {
        Insurer = insurer;
    }

    public void setBindCode(BigDecimal bindCode) {
        this.bindCode = bindCode;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setRenewableDate(String renewableDate) {
        this.renewableDate = renewableDate;
    }
}
