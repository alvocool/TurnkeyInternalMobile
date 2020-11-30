package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class ProductResponse {

    @Expose
    @SerializedName("proCode")
    private BigDecimal proCode;
    @Expose
    @SerializedName("proShtDesc")
    private BigDecimal proShtDesc;
    @Expose
    @SerializedName("proDesc")
    private String proDesc;
    @Expose
    @SerializedName("proUnwrScrCode")
    private String proUnwrScrCode;


    public BigDecimal getProCode() {
        return proCode;
    }

    public BigDecimal getProShtDesc() {
        return proShtDesc;
    }

    public String getProDesc() {
        return proDesc;
    }

    public String getProUnwrScrCode() {
        return proUnwrScrCode;
    }
}
