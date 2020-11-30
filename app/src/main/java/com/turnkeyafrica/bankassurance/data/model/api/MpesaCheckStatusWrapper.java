package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MpesaCheckStatusWrapper {

    @Expose
    @SerializedName("mpesaCode")
    String mpesaCode;

    @Expose
    @SerializedName("agentCode")
    String agentCode;

    @Expose
    @SerializedName("quoteNo")
    String quoteNo;

    @Expose
    @SerializedName("amount")
    String amount;

    @Expose
    @SerializedName("clientName")
    String clientName;

    @Expose
    @SerializedName("phoneNumber")
    String phoneNumber;

    public MpesaCheckStatusWrapper(String mpesaCode, String agentCode, String quoteNo, String amount, String clientName, String phoneNumber) {
        this.mpesaCode = mpesaCode;
        this.agentCode = agentCode;
        this.quoteNo = quoteNo;
        this.amount = amount;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
    }
}