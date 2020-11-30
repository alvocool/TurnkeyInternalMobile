package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadAttachmentList {

    @Expose
    @SerializedName("attachmentList")
    private List<UploadRequest> attachmentList;

    public List<UploadRequest> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<UploadRequest> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
