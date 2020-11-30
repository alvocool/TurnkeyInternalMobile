package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class VehicleMakesResponce {


    @Expose
    @SerializedName("vmCode")
    private BigDecimal vmCode;

    @Expose
    @SerializedName("vmMake")
    private String vmMake;

    public BigDecimal getVmCode() {
        return vmCode;
    }

    public String getVmMake() {
        return vmMake;
    }
}
