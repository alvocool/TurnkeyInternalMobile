package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiError {

    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("errorType")
    private String errorType;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("errors")
    ArrayList< String > errors = new ArrayList <> ();
    @Expose
    @SerializedName("developerMessage")
    private String developerMessage;


    public String getStatus() {
        return status;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

}

