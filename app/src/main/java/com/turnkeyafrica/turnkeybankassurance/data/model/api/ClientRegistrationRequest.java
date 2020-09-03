package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public String getClntMobileNo() {
        return clntMobileNo;
    }

    public ClientRegistrationRequest(String clntOtherNames, String clntName, String clntMobileNo, String clntEmailAddrs){

        this.clntOtherNames = clntOtherNames;
        this.clntName = clntName;
        this.clntMobileNo = clntMobileNo;
        this.clntEmailAddrs = clntEmailAddrs;

    }
}
