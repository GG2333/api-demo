package com.spring.apidemo.user;

public class User {

    public Long id;

    public String username;
    public String password;

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
