package com.spring.apidemo.data;

public class SelfResult {

    private int code;
    private String message;

    private SelfResult(int code, String message) {
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

    public static SelfResult success() {
        return new SelfResult(0, "Ok");
    }

    public static SelfResult error() {
        return new SelfResult(-1, "error");
    }

    public static SelfResult error(int code, String msg) {
        return new SelfResult(code, msg);
    }

//    public static SelfResult error400() {
//        return new SelfResult(400, "Bad Request");
//    }
//
//    public static SelfResult error404() {
//        return new SelfResult(404, "Not Found");
//    }
//
//    public static SelfResult error405() {
//        return new SelfResult(405, "Method Not Allowed");
//    }

}
