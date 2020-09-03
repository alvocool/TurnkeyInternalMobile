package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class ExtraBenefitsResponse {

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
    @SerializedName("claCode")
    private String claCode;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("excessDetails")
    private String excessDetails;

    public BigDecimal getCode() {
        return code;
    }

    public String getShtDesc() {
        return shtDesc;
    }

    public String getDesc() {
        return desc;
    }

    public String getClaCode() {
        return claCode;
    }

    public String getType() {
        return type;
    }

    public String getExcessDetails() {
        return excessDetails;
    }
}
