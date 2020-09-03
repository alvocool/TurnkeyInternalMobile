package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ClientRequest {

    @Expose
    @SerializedName("clntCode")
    private BigDecimal clntCode;
    @Expose
    @SerializedName("clntCreatedBy")
    private String clntCreatedBy;
    @Expose
    @SerializedName("clntDateCreated")
    private String clntDateCreated;
    @Expose
    @SerializedName("clntDob")
    private String clntDob;
    @Expose
    @SerializedName("clntEmailAddrs")
    private String clntEmailAddrs;
    @Expose
    @SerializedName("clntGender")
    private String clntGender;
    @Expose
    @SerializedName("clntIdRegNo")
    private String clntIdRegNo;
    @Expose
    @SerializedName("clntMaritalStatus")
    private String clntMaritalStatus;
    @Expose
    @SerializedName("clntName")
    private String clntName;
    @Expose
    @SerializedName("clntOtherNames")
    private String clntOtherNames;
    @Expose
    @SerializedName("clntPassportNo")
    private String clntPassportNo;
    @Expose
    @SerializedName("clntPin")
    private String clntPin;
    @Expose
    @SerializedName("clntPostalAddrs")
    private String clntPostalAddrs;
    @Expose
    @SerializedName("clntPwd")
    private String clntPwd;
    @Expose
    @SerializedName("clntShtDesc")
    private String clntShtDesc;
    @Expose
    @SerializedName("clntSmsTel")
    private String clntSmsTel;
    @Expose
    @SerializedName("clntStatus")
    private String clntStatus;
    @Expose
    @SerializedName("clntSurname")
    private String clntSurname;
    @Expose
    @SerializedName("clntTitle")
    private String clntTitle;
    @Expose
    @SerializedName("clntType")
    private String clntType;
    @Expose
    @SerializedName("clntUpdateDt")
    private String clntUpdateDt;
    @Expose
    @SerializedName("clntUpdatedBy")
    private String clntUpdatedBy;
    @Expose
    @SerializedName("clntWef")
    private String clntWef;


    public BigDecimal getClntCode() {
        return clntCode;
    }

    public String getClntCreatedBy() {
        return clntCreatedBy;
    }

    public String getClntDateCreated() {
        return clntDateCreated;
    }

    public String getClntDob() {
        return clntDob;
    }

    public String getClntEmailAddrs() {
        return clntEmailAddrs;
    }

    public String getClntGender() {
        return clntGender;
    }

    public String getClntIdRegNo() {
        return clntIdRegNo;
    }

    public String getClntMaritalStatus() {
        return clntMaritalStatus;
    }

    public String getClntName() {
        return clntName;
    }

    public String getClntOtherNames() {
        return clntOtherNames;
    }

    public String getClntPassportNo() {
        return clntPassportNo;
    }

    public String getClntPin() {
        return clntPin;
    }

    public String getClntPostalAddrs() {
        return clntPostalAddrs;
    }

    public String getClntPwd() {
        return clntPwd;
    }

    public String getClntShtDesc() {
        return clntShtDesc;
    }

    public String getClntSmsTel() {
        return clntSmsTel;
    }

    public String getClntStatus() {
        return clntStatus;
    }

    public String getClntSurname() {
        return clntSurname;
    }

    public String getClntTitle() {
        return clntTitle;
    }

    public String getClntType() {
        return clntType;
    }

    public String getClntUpdateDt() {
        return clntUpdateDt;
    }

    public String getClntUpdatedBy() {
        return clntUpdatedBy;
    }

    public String getClntWef() {
        return clntWef;
    }
}
