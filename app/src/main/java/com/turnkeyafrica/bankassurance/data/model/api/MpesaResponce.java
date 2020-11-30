package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MpesaResponce {

    @Expose
    @SerializedName("ResponseCode")
    private String ResponseCode;
    @Expose
    @SerializedName("ResponseDescription")
    private String ResponseDescription;
    @Expose
    @SerializedName("MerchantRequestID")
    private String MerchantRequestID;
    @Expose
    @SerializedName("CheckoutRequestID")
    private String CheckoutRequestID;
    @Expose
    @SerializedName("transCode")
    private String transCode;
    @Expose
    @SerializedName("message")
    private String message;

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getResponseDescription() {
        return ResponseDescription;
    }

    public String getMerchantRequestID() {
        return MerchantRequestID;
    }

    public String getCheckoutRequestID() {
        return CheckoutRequestID;
    }

    public String getTransCode() {
        return transCode;
    }

    public String getMessage() {
        return message;
    }
}
