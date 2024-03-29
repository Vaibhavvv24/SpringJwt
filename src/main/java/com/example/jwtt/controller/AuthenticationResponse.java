package com.example.jwtt.controller;

import lombok.Builder;
import lombok.Data;

public class AuthenticationResponse {

    private String token;
    public AuthenticationResponse(){

    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public static AuthenticationResponse authenticate(String token){
        return new AuthenticationResponse(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
