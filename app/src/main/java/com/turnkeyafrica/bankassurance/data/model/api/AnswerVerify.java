package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerVerify {

    @Expose
    @SerializedName("securityQuestionCode")
    Integer securityQuestionCode;

    @Expose
    @SerializedName("phoneNumber")
    String phoneNumber;

    @Expose
    @SerializedName("answer")
    String answer;

    public AnswerVerify(Integer securityQuestionCode, String phoneNumber, String answer) {
        this.securityQuestionCode = securityQuestionCode;
        this.phoneNumber = phoneNumber;
        this.answer = answer;
    }
}
