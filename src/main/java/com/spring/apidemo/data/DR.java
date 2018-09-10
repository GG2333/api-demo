package com.spring.apidemo.data;

public class DR<T> {

    public int size;
    public int number;
    public int numberOfElements;
    public int totalPages;
    public long totalElements;
    public boolean isLast;

    public T data;

    public DR(T data) {
        this.data = data;
    }

    public static <T> DR<T> create(T data) {
        return new DR<>(data);
    }

    @Override
    public String toString() {
        return "DR{" +
                "data=" + data +
                '}';
    }
}
