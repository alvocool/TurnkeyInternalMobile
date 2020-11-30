package com.turnkeyafrica.bankassurance.data.model.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class MainDetailsQuote {

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
    @SerializedName("quotSaved")
    private String quotSaved;

    @Expose
    @SerializedName("coverType")
    private String coverType;

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
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

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setInsurer(String insurer) {
        Insurer = insurer;
    }

    public void setBindCode(BigDecimal bindCode) {
        this.bindCode = bindCode;
    }

    public String getQuotSaved() {
        return quotSaved;
    }

    public void setQuotSaved(String quotSaved) {
        this.quotSaved = quotSaved;
    }
}
