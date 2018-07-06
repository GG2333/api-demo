package com.spring.apidemo.data;

public class DR<T> {

    public T data;


    public DR(T data) {
        this.data = data;
    }

    public static <T> DR<T> create(T data) {
        return new DR<>(data);
    }

}
