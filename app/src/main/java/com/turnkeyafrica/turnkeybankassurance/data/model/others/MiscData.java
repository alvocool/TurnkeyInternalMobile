package com.turnkeyafrica.turnkeybankassurance.data.model.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MiscData {
    @Expose
    @SerializedName("id")
    private String idNumber;

    @Expose
    @SerializedName("kra")
    private String KraNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getKraNumber() {
        return KraNumber;
    }

    public void setKraNumber(String kraNumber) {
        KraNumber = kraNumber;
    }
}
