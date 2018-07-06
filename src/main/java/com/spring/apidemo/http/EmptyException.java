package com.spring.apidemo.http;

public class EmptyException extends Exception {

    public EmptyException() {
    }

    public EmptyException(String message) {
        super(message);
    }
}
