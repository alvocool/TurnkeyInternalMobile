package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MpesaCheckModel {

    @Expose
    @SerializedName("CheckoutRequestID")
    String CheckoutRequestID;

    @Expose
    @SerializedName("quoteCode")
    BigDecimal quoteCode;

    @Expose
    @SerializedName("agentCode")
    BigDecimal agentCode;

    @Expose
    @SerializedName("amount")
    BigDecimal amount;

    public String getCheckoutRequestID() {
        return CheckoutRequestID;
    }

    public BigDecimal getQuoteCode() {
        return quoteCode;
    }

    public BigDecimal getAgentCode() {
        return agentCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MpesaCheckModel(String checkoutRequestID, BigDecimal quoteCode, BigDecimal agentCode, BigDecimal amount) {
        CheckoutRequestID = checkoutRequestID;
        this.quoteCode = quoteCode;
        this.agentCode = agentCode;
        this.amount = amount;
    }


}
