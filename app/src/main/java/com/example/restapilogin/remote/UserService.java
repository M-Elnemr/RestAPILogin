package com.example.restapilogin.remote;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("LoginController/Login")
    Call<LoginResponse> createUser(@Body LoginResponse login);

}
