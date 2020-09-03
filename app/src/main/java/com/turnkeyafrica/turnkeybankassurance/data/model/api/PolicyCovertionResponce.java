package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class PolicyCovertionResponce {

    @Expose
    @SerializedName("batchNo")
    private BigDecimal batchNo;
    @Expose
    @SerializedName("policyNo")
    private String policyNo;
    @Expose
    @SerializedName("transmittalNo")
    private String transmittalNo;

    public BigDecimal getBatchNo() {
        return batchNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public String getTransmittalNo() {
        return transmittalNo;
    }
}
