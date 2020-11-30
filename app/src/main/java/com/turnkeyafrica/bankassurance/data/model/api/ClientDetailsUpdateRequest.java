package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientDetailsUpdateRequest {

    @Expose
    @SerializedName("clntCode")
    private String clntCode;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @Expose
    @SerializedName("firstName")
    private String firstName;
    @Expose
    @SerializedName("surname")
    private String surname;

    public ClientDetailsUpdateRequest(String clntCode, String email, String phoneNumber, String firstName, String surname) {
        this.clntCode = clntCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getClntCode() {
        return clntCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }
}
