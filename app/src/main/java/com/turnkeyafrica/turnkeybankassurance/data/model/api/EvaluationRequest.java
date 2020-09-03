package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.List;

public class EvaluationRequest {


    @Expose
    @SerializedName("rptCode")
    private BigDecimal rptCode;
    @Expose
    @SerializedName("encodeFormat")
    private String encodeFormat;
    @Expose
    @SerializedName("params")
    private List<String> params;
    @Expose
    @SerializedName("reportFormat")
    private String reportFormat;

    public BigDecimal getRptCode() {
        return rptCode;
    }

    public void setRptCode(BigDecimal rptCode) {
        this.rptCode = rptCode;
    }

    public String getEncodeFormat() {
        return encodeFormat;
    }

    public void setEncodeFormat(String encodeFormat) {
        this.encodeFormat = encodeFormat;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }
}
