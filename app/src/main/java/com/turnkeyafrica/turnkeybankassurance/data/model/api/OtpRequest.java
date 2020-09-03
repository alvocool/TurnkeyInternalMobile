package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpRequest {

    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @Expose
    @SerializedName("resetCode")
    private String resetCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }
}
