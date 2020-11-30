package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PolicyResponce {

    @Expose
    @SerializedName("batchNo")
    private BigDecimal batchNo;
    @Expose
    @SerializedName("policyNo")
    private String policyNo;
    @Expose
    @SerializedName("wefDt")
    private String wefDt;
    @Expose
    @SerializedName("wetDt")
    private String wetDt;
    @Expose
    @SerializedName("uwYear")
    private BigDecimal uwYear;
    @Expose
    @SerializedName("totalSumInsured")
    private BigDecimal totalSumInsured;
    @Expose
    @SerializedName("policyStatus")
    private String policyStatus;
    @Expose
    @SerializedName("nettPremium")
    private BigDecimal nettPremium;
    @Expose
    @SerializedName("preparedBy")
    private String preparedBy;
    @Expose
    @SerializedName("preparedDate")
    private String preparedDate;
    @Expose
    @SerializedName("currentStatus")
    private String currentStatus;
    @Expose
    @SerializedName("authosrised")
    private String authosrised;
    @Expose
    @SerializedName("proCode")
    private BigDecimal proCode;
    @Expose
    @SerializedName("renewedRec")
    private String renewedRec;
    @Expose
    @SerializedName("polReinsured")
    private String polReinsured;
    @Expose
    @SerializedName("renewable")
    private String renewable;
    @Expose
    @SerializedName("policyCoverTo")
    private String policyCoverTo;
    @Expose
    @SerializedName("policyCoverFrom")
    private String policyCoverFrom;
    @Expose
    @SerializedName("siDiff")
    private BigDecimal siDiff;
    @Expose
    @SerializedName("wtht")
    private BigDecimal wtht;
    @Expose
    @SerializedName("premTax")
    private BigDecimal premTax;
    @Expose
    @SerializedName("renewalDate")
    private String renewalDate;
    @Expose
    @SerializedName("prpCode")
    private BigDecimal prpCode;
    @Expose
    @SerializedName("totalFap")
    private BigDecimal totalFap;
    @Expose
    @SerializedName("totalGp")
    private BigDecimal totalGp;
    @Expose
    @SerializedName("agnCode")
    private BigDecimal agnCode;
    @Expose
    @SerializedName("polFreqOfPayment")
    private String polFreqOfPayment;
    @Expose
    @SerializedName("risks")
    ArrayList<RiskResponce> risks = new ArrayList <RiskResponce> ();
    @Expose
    @SerializedName("ProductObject")
    ProductResponse ProductObject;
    @Expose
    @SerializedName("ClientObject")
    ClientRequest ClientObject;

    @Expose
    @SerializedName("polWebBindName")
    String polWebBindName;

    public BigDecimal getBatchNo() {
        return batchNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public String getWefDt() {
        return wefDt;
    }

    public String getWetDt() {
        return wetDt;
    }

    public BigDecimal getUwYear() {
        return uwYear;
    }

    public BigDecimal getTotalSumInsured() {
        return totalSumInsured;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public BigDecimal getNettPremium() {
        return nettPremium;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public String getPreparedDate() {
        return preparedDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getAuthosrised() {
        return authosrised;
    }

    public BigDecimal getProCode() {
        return proCode;
    }

    public String getRenewedRec() {
        return renewedRec;
    }

    public String getPolReinsured() {
        return polReinsured;
    }

    public String getRenewable() {
        return renewable;
    }

    public String getPolicyCoverTo() {
        return policyCoverTo;
    }

    public String getPolicyCoverFrom() {
        return policyCoverFrom;
    }

    public BigDecimal getSiDiff() {
        return siDiff;
    }

    public BigDecimal getWtht() {
        return wtht;
    }

    public BigDecimal getPremTax() {
        return premTax;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public BigDecimal getPrpCode() {
        return prpCode;
    }

    public BigDecimal getTotalFap() {
        return totalFap;
    }

    public BigDecimal getTotalGp() {
        return totalGp;
    }

    public BigDecimal getAgnCode() {
        return agnCode;
    }

    public String getPolFreqOfPayment() {
        return polFreqOfPayment;
    }

    public ArrayList<RiskResponce> getRisks() {
        return risks;
    }

    public ProductResponse getProductObject() {
        return ProductObject;
    }

    public ClientRequest getClientObject() {
        return ClientObject;
    }

    public String getPolWebBindName() {
        return polWebBindName;
    }
}
