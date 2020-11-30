package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuoteToPolWrapper {

    @Expose
    @SerializedName("quoteCode")
    String quoteCode;

    @Expose
    @SerializedName("idNumber")
    String idNumber;

    @Expose
    @SerializedName("kraPin")
    String kraPin;

    @Expose
    @SerializedName("amountPaid")
    String amountPaid;

    public QuoteToPolWrapper(String quoteCode, String idNumber, String kraPin, String amountPaid) {
        this.quoteCode = quoteCode;
        this.idNumber = idNumber;
        this.kraPin = kraPin;
        this.amountPaid = amountPaid;
    }
}
