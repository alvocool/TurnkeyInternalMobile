package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ServiceRequestType {

    @Expose
    @SerializedName("code")
    private BigDecimal code;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("platform")
    private String platform;

    @Expose
    @SerializedName("priority")
    private BigDecimal priority;

    public BigDecimal getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public BigDecimal getPriority() {
        return priority;
    }
}
