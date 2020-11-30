package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsWrapper {

    @Expose
    @SerializedName("value")
    private String value;

    @Expose
    @SerializedName("status")
    private String status;

    public NotificationsWrapper(String value, String status) {
        this.value = value;
        this.status = status;
    }
}
