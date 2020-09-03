package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class SendClaimDetailsResponse {

    @Expose
    @SerializedName("clientCode")
    private Long clientCode;

    @Expose
    @SerializedName("incidentDesc")
    private String incidentDesc;

    @Expose
    @SerializedName("lossDate")
    private String lossDate;

    @Expose
    @SerializedName("polBatchNo")
    private BigDecimal polBatchNo;

    @Expose
    @SerializedName("policyNumber")
    private String policyNumber;

    @Expose
    @SerializedName("riskId")
    private String riskId;

    @Expose
    @SerializedName("files")
    private List<ClaimDocumentsResponce> files;

    public Long getClientCode() {
        return clientCode;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    public String getIncidentDesc() {
        return incidentDesc;
    }

    public void setIncidentDesc(String incidentDesc) {
        this.incidentDesc = incidentDesc;
    }

    public String getLossDate() {
        return lossDate;
    }

    public void setLossDate(String lossDate) {
        this.lossDate = lossDate;
    }

    public BigDecimal getPolBatchNo() {
        return polBatchNo;
    }

    public void setPolBatchNo(BigDecimal polBatchNo) {
        this.polBatchNo = polBatchNo;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public List<ClaimDocumentsResponce> getFiles() {
        return files;
    }

    public void setFiles(List<ClaimDocumentsResponce> files) {
        this.files = files;
    }
}
