package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class SimpleOtpRequest {

    @Expose
    @SerializedName("phoneNumber")
    String Number;

    @Expose
    @SerializedName("pin")
    String Pin;

    public SimpleOtpRequest(String number, String pin) {
        Number = number;
        Pin = pin;
    }
}
