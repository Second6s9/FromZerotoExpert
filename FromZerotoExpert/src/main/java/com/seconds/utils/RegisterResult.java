package com.seconds.utils;

public class RegisterResult {
    private Boolean isQualified;
    private String message;

    public RegisterResult() {
    }

    public RegisterResult(Boolean isQualified, String message) {
        this.isQualified = isQualified;
        this.message = message;
    }

    public RegisterResult(Boolean isQualified) {
        this.isQualified = isQualified;
    }

    public Boolean getQualified() {
        return isQualified;
    }

    public void setQualified(Boolean qualified) {
        isQualified = qualified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RegisterResult{" +
                "isQualified=" + isQualified +
                ", message='" + message + '\'' +
                '}';
    }
}
