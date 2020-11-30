package com.turnkeyafrica.bankassurance.data.model.others;

public class LocalError {

    private int code;

    private String message;

    public LocalError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
