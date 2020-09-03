package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class CardDetailsRequest {

    @Expose
    @SerializedName("amount")
    private String amount;
    @Expose
    @SerializedName("clientCode")
    private BigDecimal clientCode;
    @Expose
    @SerializedName("countryCode")
    private String countryCode;
    @Expose
    @SerializedName("crediCardNumber")
    private String creditCardNumber;
    @Expose
    @SerializedName("currency")
    private String currency;
    @Expose
    @SerializedName("cvv")
    private String cvv;
    @Expose
    @SerializedName("expiryMonth")
    private String expiryMonth;
    @Expose
    @SerializedName("expiryYear")
    private String expiryYear;
    @Expose
    @SerializedName("nameOnCard")
    private String nameOnCard;
    @Expose
    @SerializedName("refNo")
    private String refNo;
    @Expose
    @SerializedName("transType")
    private String transType;

    public CardDetailsRequest(String amount, BigDecimal clientCode, String countryCode, String creditCardNumber, String currency, String cvv, String expiryMonth, String expiryYear, String nameOnCard, String refNo, String transType) {
        this.amount = amount;
        this.clientCode = clientCode;
        this.countryCode = countryCode;
        this.creditCardNumber = creditCardNumber;
        this.currency = currency;
        this.cvv = cvv;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.nameOnCard = nameOnCard;
        this.refNo = refNo;
        this.transType = transType;
    }

    public String getAmount() {
        return amount;
    }
}
