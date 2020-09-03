package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardDetailsResponce {

    @Expose
    @SerializedName("transactionStatus")
    private String transactionStatus;
    @Expose
    @SerializedName("authorizationCode")
    private String authorizationCode;
    @Expose
    @SerializedName("captureAmount")
    private String captureAmount;
    @Expose
    @SerializedName("captureReconciliationId")
    private String captureReconciliationId;
    @Expose
    @SerializedName("authReconciliationId")
    private String authReconciliationId;
    @Expose
    @SerializedName("requestId")
    private String requestId;

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getCaptureAmount() {
        return captureAmount;
    }

    public String getCaptureReconciliationId() {
        return captureReconciliationId;
    }

    public String getAuthReconciliationId() {
        return authReconciliationId;
    }

    public String getRequestId() {
        return requestId;
    }
}
