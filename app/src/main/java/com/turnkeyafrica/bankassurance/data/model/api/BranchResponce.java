package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class BranchResponce {

    @Expose
    @SerializedName("code")
    private BigDecimal code;
    @Expose
    @SerializedName("shtDesc")
    private String shtDesc;
    @Expose
    @SerializedName("desc")
    private String desc;
    @Expose
    @SerializedName("physicalAddress")
    private String physicalAddress;
    @Expose
    @SerializedName("brnRegCode")
    private BigDecimal brnRegCode;


    public BigDecimal getCode() {
        return code;
    }

    public String getShtDesc() {
        return shtDesc;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public BigDecimal getBrnRegCode() {
        return brnRegCode;
    }
}
