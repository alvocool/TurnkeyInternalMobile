package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class ValuersResponce {

    @Expose
    @SerializedName("sprCode")
    private BigDecimal sprCode;
    @Expose
    @SerializedName("sprEmail")
    private String sprEmail;
    @Expose
    @SerializedName("sprIprsValidated")
    private String sprIprsValidated;
    @Expose
    @SerializedName("sprLocation")
    private String sprLocation;
    @Expose
    @SerializedName("sprMobileNo")
    private String sprMobileNo;
    @Expose
    @SerializedName("sprName")
    private String sprName;
    @Expose
    @SerializedName("sprPhone")
    private String sprPhone;
    @Expose
    @SerializedName("sprPhysicalAddress")
    private String sprPhysicalAddress;
    @Expose
    @SerializedName("sprPostalAddress")
    private String sprPostalAddress;
    @Expose
    @SerializedName("sprSmsNumber")
    private String sprSmsNumber;
    @Expose
    @SerializedName("sprSptCode")
    private BigDecimal sprSptCode;
    @Expose
    @SerializedName("sprStatus")
    private String sprStatus;

    public BigDecimal getSprCode() {
        return sprCode;
    }

    public String getSprEmail() {
        return sprEmail;
    }

    public String getSprIprsValidated() {
        return sprIprsValidated;
    }

    public String getSprLocation() {
        return sprLocation;
    }

    public String getSprMobileNo() {
        return sprMobileNo;
    }

    public String getSprName() {
        return sprName;
    }

    public String getSprPhone() {
        return sprPhone;
    }

    public String getSprPhysicalAddress() {
        return sprPhysicalAddress;
    }

    public String getSprPostalAddress() {
        return sprPostalAddress;
    }

    public String getSprSmsNumber() {
        return sprSmsNumber;
    }

    public BigDecimal getSprSptCode() {
        return sprSptCode;
    }

    public String getSprStatus() {
        return sprStatus;
    }
}
