package com.turnkeyafrica.bankassurance.data.model.api;

import java.math.BigDecimal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MpesaCheckStatusResponce {

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
    @SerializedName("ResultCode")
    private BigDecimal ResultCode;
    @Expose
    @SerializedName("ResultDesc")
    private String ResultDesc;
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

    public BigDecimal getResultCode() {
        return ResultCode;
    }

    public String getResultDesc() {
        return ResultDesc;
    }

    public String getTransCode() {
        return transCode;
    }

    public String getMessage() {
        return message;
    }
}
