package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorityObject {
    public String getAuthority() {
        return authority;
    }

    @Expose
    @SerializedName("authority")
    private String authority;

}
