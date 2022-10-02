package com.seconds.utils;

public class BaseResult {
    private Object data;
    private Boolean isQualified;
    private String message;

    public BaseResult() {
    }

    public BaseResult(Object data, Boolean isQualified, String message) {
        this.data = data;
        this.isQualified = isQualified;
        this.message = message;
    }

    public BaseResult(Object data, Boolean isQualified) {
        this.data = data;
        this.isQualified = isQualified;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
}
