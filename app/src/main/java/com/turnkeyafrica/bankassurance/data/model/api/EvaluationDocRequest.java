package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EvaluationDocRequest {

    @Expose
    @SerializedName("encodeFormat")
    private String encodeFormat;
    @Expose
    @SerializedName("params")
    private ArrayList < reportParams > params;
    @Expose
    @SerializedName("reportFormat")
    private String reportFormat;
    @Expose
    @SerializedName("rptCode")
    private Long rptCode;

    public EvaluationDocRequest(String encodeFormat, ArrayList<reportParams> params, String reportFormat, Long rptCode) {
        this.encodeFormat = encodeFormat;
        this.params = params;
        this.reportFormat = reportFormat;
        this.rptCode = rptCode;
    }

    public String getEncodeFormat() {
        return encodeFormat;
    }

    public ArrayList<reportParams> getParams() {
        return params;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public Long getRptCode() {
        return rptCode;
    }

    public  static class reportParams {

        @Expose
        @SerializedName("name")
        public String paramnName;
        @Expose
        @SerializedName("value")
        public Long value;

        public String getParamnName() {
            return paramnName;
        }

        public void setParamnName(String paramnName) {
            this.paramnName = paramnName;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }
}
