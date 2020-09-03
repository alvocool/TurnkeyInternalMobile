package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class DetailsResponce {

    @Expose
    @SerializedName("schvCode")
    private BigDecimal schvCode;

    @Expose
    @SerializedName("schvNarration")
    private String schvNarration;

    @Expose
    @SerializedName("schvType")
    private String schvType;

    @Expose
    @SerializedName("schvValue")
    private String schvValue;

    @Expose
    @SerializedName("schvBindCode")
    private BigDecimal schvBindCode;

    public BigDecimal getSchvCode() {
        return schvCode;
    }

    public String getSchvNarration() {
        return schvNarration;
    }

    public String getSchvType() {
        return schvType;
    }

    public String getSchvValue() {
        return schvValue;
    }

    public BigDecimal getSchvBindCode() {
        return schvBindCode;
    }
}
