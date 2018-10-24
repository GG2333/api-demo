package com.spring.apidemo.data;

public class SelfException extends Exception {

    private int code;
    private String message;

    public SelfException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
