package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class CoverTypesResponce {


    @Expose
    @SerializedName("code")
    private BigDecimal code;

    @Expose
    @SerializedName("shtDesc")
    private String shtDesc;

    @Expose
    @SerializedName("desc")
    private String desc;

    public BigDecimal getCode() {
        return code;
    }

    public String getShtDesc() {
        return shtDesc;
    }

    public String getDesc() {
        return desc;
    }
}
