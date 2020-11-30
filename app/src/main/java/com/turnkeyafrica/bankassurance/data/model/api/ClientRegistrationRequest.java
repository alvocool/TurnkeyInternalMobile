package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClientRegistrationRequest {


    @Expose
    @SerializedName("prsEmailAddrs")
    private String clntEmailAddrs;
    @Expose
    @SerializedName("prsMobileNo")
    private String clntMobileNo;
    @Expose
    @SerializedName("prsSurname")
    private String clntName;
    @Expose
    @SerializedName("prsOtherNames")
    private String clntOtherNames;
    @Expose
    @SerializedName("prsPassword")
    private String clntPassword;
    @Expose
    @SerializedName("userSecurityQuestions")
    private List<UserSecurityQuestions> userSecurityQuestions;


    public String getClntMobileNo() {
        return clntMobileNo;
    }

    public String getClntPassword() {
        return clntPassword;
    }

    public ClientRegistrationRequest(String clntOtherNames, String clntName, String clntMobileNo, String clntEmailAddrs){

        this.clntOtherNames = clntOtherNames;
        this.clntName = clntName;
        this.clntMobileNo = clntMobileNo;
        this.clntEmailAddrs = clntEmailAddrs;

    }

    public String getClntEmailAddrs() {
        return clntEmailAddrs;
    }

    public void setClntPassword(String clntPassword) {
        this.clntPassword = clntPassword;
    }

    public void setUserSecurityQuestions(List<UserSecurityQuestions> userSecurityQuestions) {
        this.userSecurityQuestions = userSecurityQuestions;
    }
}
