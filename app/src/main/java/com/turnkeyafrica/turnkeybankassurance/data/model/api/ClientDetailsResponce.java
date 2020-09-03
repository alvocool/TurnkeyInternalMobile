package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ClientDetailsResponce {

    @Expose
    @SerializedName("acwaCode")
    private BigDecimal acwaCode;
    @Expose
    @SerializedName("acwaUsername")
    private String acwaUsername;
    @Expose
    @SerializedName("acwaLoginAllowed")
    private String acwaLoginAllowed;
    @Expose
    @SerializedName("acwaPwdChangedAt")
    private String acwaPwdChangedAt;
    @Expose
    @SerializedName("acwaPwdReset")
    private String acwaPwdReset;
    @Expose
    @SerializedName("acwaLoginAttempts")
    private BigDecimal acwaLoginAttempts;
    @Expose
    @SerializedName("acwaPersonelRank")
    private String acwaPersonelRank;
    @Expose
    @SerializedName("acwaDtCreated")
    private String acwaDtCreated;
    @Expose
    @SerializedName("acwaPrsCode")
    private BigDecimal acwaPrsCode;
    @Expose
    @SerializedName("acwaStatus")
    private String acwaStatus;
    @Expose
    @SerializedName("acwaLastLoginDate")
    private String acwaLastLoginDate;
    @Expose
    @SerializedName("acwaClntCode")
    private BigDecimal acwaClntCode;
    @Expose
    @SerializedName("acwaCreatedBy")
    private String acwaCreatedBy;
    @Expose
    @SerializedName("acwaName")
    private String acwaName;
    @Expose
    @SerializedName("acwaEmailAddrs")
    private String acwaEmailAddrs;
    @Expose
    @SerializedName("acwaType")
    private String acwaType;
    @Expose
    @SerializedName("acwaCountry")
    private String acwaCountry;
    @Expose
    @SerializedName("acwaIdRegNo")
    private String acwaIdRegNo;
    @Expose
    @SerializedName("acwaSmsCode")
    private String acwaSmsCode;
    @Expose
    @SerializedName("acwaMobileNumber")
    private String acwaMobileNumber;
    @Expose
    @SerializedName("acwaPassportNo")
    private String acwaPassportNo;
    @Expose
    @SerializedName("acwaEmailVerified")
    private String acwaEmailVerified;
    @Expose
    @SerializedName("authorities")
    private ArrayList < AuthorityObject > authorities = new ArrayList< AuthorityObject >();


    public BigDecimal getAcwaCode() {
        return acwaCode;
    }

    public String getAcwaUsername() {
        return acwaUsername;
    }

    public String getAcwaLoginAllowed() {
        return acwaLoginAllowed;
    }

    public String getAcwaPwdChangedAt() {
        return acwaPwdChangedAt;
    }

    public String getAcwaPwdReset() {
        return acwaPwdReset;
    }

    public BigDecimal getAcwaLoginAttempts() {
        return acwaLoginAttempts;
    }

    public String getAcwaPersonelRank() {
        return acwaPersonelRank;
    }

    public String getAcwaDtCreated() {
        return acwaDtCreated;
    }

    public String getAcwaStatus() {
        return acwaStatus;
    }

    public String getAcwaLastLoginDate() {
        return acwaLastLoginDate;
    }

    public BigDecimal getAcwaClntCode() {
        return acwaClntCode;
    }

    public String getAcwaCreatedBy() {
        return acwaCreatedBy;
    }

    public String getAcwaName() {
        return acwaName;
    }

    public String getAcwaEmailAddrs() {
        return acwaEmailAddrs;
    }

    public String getAcwaType() {
        return acwaType;
    }

    public String getAcwaCountry() {
        return acwaCountry;
    }

    public String getAcwaIdRegNo() {
        return acwaIdRegNo;
    }

    public String getAcwaSmsCode() {
        return acwaSmsCode;
    }

    public String getAcwaMobileNumber() {
        return acwaMobileNumber;
    }

    public String getAcwaPassportNo() {
        return acwaPassportNo;
    }

    public String getAcwaEmailVerified() {
        return acwaEmailVerified;
    }

    public ArrayList<AuthorityObject> getAuthorities() {
        return authorities;
    }

    public BigDecimal getAcwaPrsCode() {
        return acwaPrsCode;
    }
}
