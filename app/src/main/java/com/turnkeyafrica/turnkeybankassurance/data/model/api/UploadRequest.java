package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadRequest {

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("clientCode")
    private String clientCode;

    @Expose
    @SerializedName("docName")
    private String docName;

    @Expose
    @SerializedName("polNo")
    private String polNo;

    @Expose
    @SerializedName("transType")
    private String transType;


    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPolNo() {
        return polNo;
    }

    public void setPolNo(String polNo) {
        this.polNo = polNo;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
}
