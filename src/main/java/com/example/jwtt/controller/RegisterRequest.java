package com.example.jwtt.controller;

import lombok.Builder;
import lombok.Data;

public class RegisterRequest {
    private String firstname;

    private String lastname;

    private String email;
    private String pwd;
    public RegisterRequest()
    {

    }

    public RegisterRequest(String firstName, String lastName, String email, String pwd) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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
