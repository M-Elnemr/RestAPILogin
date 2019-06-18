package com.example.restapilogin.remote;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("email")
    public String email;
    @SerializedName("first_name")
    public String FirstName;
    @SerializedName("last_name")
    public String LastName;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("Password")
    public String Password;
    @SerializedName("ResponseCode")
    public String ResponseCode;
    @SerializedName("message")
    public String ResponseMessage;

    public LoginResponse(String email, String Password) {
        this.email = email;
        this.Password = Password;
    }


    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }


    public String getResponseCode() {
        return ResponseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return Password;
    }
}