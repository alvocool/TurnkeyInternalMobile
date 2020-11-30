package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWrapper {

    @Expose
    @SerializedName("value")
    private String value;

    public DataWrapper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
