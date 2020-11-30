package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSecurityQuestions {

    @Expose
    @SerializedName("answer")
    String Answer;

    @Expose
    @SerializedName("code")
    Integer Code;

    @Expose
    @SerializedName("question")
    String Question;

    public String getAnswer() {
        return Answer;
    }

    public UserSecurityQuestions(String answer, Integer code, String question) {
        Answer = answer;
        Code = code;
        Question = question;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
