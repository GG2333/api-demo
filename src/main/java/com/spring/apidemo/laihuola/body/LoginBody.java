package com.spring.apidemo.laihuola.body;

public class LoginBody {

    private String phoneNumber;
    private String captchas;

    public LoginBody(String phoneNumber, String captchas) {
        this.phoneNumber = phoneNumber;
        this.captchas = captchas;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCaptchas() {
        return captchas;
    }

    public void setCaptchas(String captchas) {
        this.captchas = captchas;
    }

}
