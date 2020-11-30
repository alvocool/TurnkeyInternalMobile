package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityQuestions {

    @Expose
    @SerializedName("code")
    Integer Code;

    @Expose
    @SerializedName("description")
    String Description;

    public Integer getCode() {
        return Code;
    }

    public String getDescription() {
        return Description;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
