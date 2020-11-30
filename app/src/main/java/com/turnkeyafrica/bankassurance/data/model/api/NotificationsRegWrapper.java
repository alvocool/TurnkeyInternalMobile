package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsRegWrapper {

    @Expose
    @SerializedName("value")
    private String value;

    @Expose
    @SerializedName("uuid")
    private String uuid;

    @Expose
    @SerializedName("code")
    private String code;

    public NotificationsRegWrapper(String value, String uuid, String code) {
        this.value = value;
        this.uuid = uuid;
        this.code = code;
    }
}
