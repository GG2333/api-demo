package com.spring.apidemo.data;

public class RR {

    public int code;
    public String message;

    public boolean isSuccessful() {
        return code == 0;
    }

}
