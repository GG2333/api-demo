package com.spring.apidemo.data;

public class RR {

    private int code;
    private String message;

    private RR(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RR success() {
        return new RR(0, "ok");
    }

    public static RR error() {
        return new RR(-1, "error");
    }


}
