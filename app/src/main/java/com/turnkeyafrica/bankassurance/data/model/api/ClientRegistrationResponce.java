package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientRegistrationResponce {

    @Expose
    @SerializedName("result")
    private String result;

    @Expose
    @SerializedName("setOrExpire")
    private String setOrExpire;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSetOrExpire() {
        return setOrExpire;
    }

    public void setSetOrExpire(String setOrExpire) {
        this.setOrExpire = setOrExpire;
    }

}
