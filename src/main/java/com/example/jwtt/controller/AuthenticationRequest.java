package com.example.jwtt.controller;

import lombok.Builder;
import lombok.Data;

public class AuthenticationRequest {
    private String email;

    private String pwd;

    public AuthenticationRequest(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
    public AuthenticationRequest(){

    }

    @Override
    public String toString() {
        return "AuthentcationRequest{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
