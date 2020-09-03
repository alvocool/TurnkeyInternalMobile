package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class BranchRegionResponce {

    @Expose
    @SerializedName("regCode")
    private BigDecimal regCode;
    @Expose
    @SerializedName("regOrgCode")
    private BigDecimal regOrgCode;
    @Expose
    @SerializedName("regShtDesc")
    private String regShtDesc;
    @Expose
    @SerializedName("regName")
    private String regName;

    public BigDecimal getRegCode() {
        return regCode;
    }

    public void setRegCode(BigDecimal regCode) {
        this.regCode = regCode;
    }

    public BigDecimal getRegOrgCode() {
        return regOrgCode;
    }

    public void setRegOrgCode(BigDecimal regOrgCode) {
        this.regOrgCode = regOrgCode;
    }

    public String getRegShtDesc() {
        return regShtDesc;
    }

    public void setRegShtDesc(String regShtDesc) {
        this.regShtDesc = regShtDesc;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }
}
