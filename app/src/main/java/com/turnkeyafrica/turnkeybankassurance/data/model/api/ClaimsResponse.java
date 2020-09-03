package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ClaimsResponse {

    @Expose
    @SerializedName("cmbBookedBy")
    private String cmbBookedBy;
    @Expose
    @SerializedName("cmbBookedDate")
    private String cmbBookedDate;
    @Expose
    @SerializedName("cmbClaimDate")
    private String cmbClaimDate;
    @Expose
    @SerializedName("cmbClaimNo")
    private String cmbClaimNo;
    @Expose
    @SerializedName("cmbClaimStatus")
    private String cmbClaimStatus;
    @Expose
    @SerializedName("cmbClaimClientPrpCode")
    private BigDecimal cmbClaimClientPrpCode;
    @Expose
    @SerializedName("cmbClientClaimShtDesc")
    private String cmbClientClaimShtDesc;
    @Expose
    @SerializedName("cmbCovtCode")
    private BigDecimal cmbCovtCode;
    @Expose
    @SerializedName("cmbCovtShtDesc")
    private String cmbCovtShtDesc;
    @Expose
    @SerializedName("cmbPropertyId")
    private String cmbPropertyId;
    @Expose
    @SerializedName("cmbLossDateTime")
    private String cmbLossDateTime;
    @Expose
    @SerializedName("cmbLossDesc")
    private String cmbLossDesc;
    @Expose
    @SerializedName("cmbProCode")
    private BigDecimal cmbProCode;
    @Expose
    @SerializedName("cmbPrpCode")
    private BigDecimal cmbPrpCode;

    @Expose
    @SerializedName("cmbAgentName")
    private String cmbAgentName;

    @Expose
    @SerializedName("cmbPolicyNo")
    private String cmbPolicyNo;

    public String getCmbAgentName() {
        return cmbAgentName;
    }

    public String getCmbPolicyNo() {
        return cmbPolicyNo;
    }

    public String getCmbBookedBy() {
        return cmbBookedBy;
    }

    public String getCmbBookedDate() {
        return cmbBookedDate;
    }

    public String getCmbClaimDate() {
        return cmbClaimDate;
    }

    public String getCmbClaimNo() {
        return cmbClaimNo;
    }

    public String getCmbClaimStatus() {
        return cmbClaimStatus;
    }

    public BigDecimal getCmbClaimClientPrpCode() {
        return cmbClaimClientPrpCode;
    }

    public String getCmbClientClaimShtDesc() {
        return cmbClientClaimShtDesc;
    }

    public BigDecimal getCmbCovtCode() {
        return cmbCovtCode;
    }

    public String getCmbCovtShtDesc() {
        return cmbCovtShtDesc;
    }

    public String getCmbPropertyId() {
        return cmbPropertyId;
    }

    public String getCmbLossDateTime() {
        return cmbLossDateTime;
    }

    public String getCmbLossDesc() {
        return cmbLossDesc;
    }

    public BigDecimal getCmbProCode() {
        return cmbProCode;
    }

    public BigDecimal getCmbPrpCode() {
        return cmbPrpCode;
    }
}
