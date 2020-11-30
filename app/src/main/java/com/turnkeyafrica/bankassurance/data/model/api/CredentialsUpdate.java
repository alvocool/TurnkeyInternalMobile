package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CredentialsUpdate {

    @Expose
    @SerializedName("phoneNumber")
    String phoneNumber;

    @Expose
    @SerializedName("password")
    String password;

    @Expose
    @SerializedName("uuid")
    String uuid;

    public CredentialsUpdate(String phoneNumber, String password, String uuid) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.uuid = uuid;
    }
}
