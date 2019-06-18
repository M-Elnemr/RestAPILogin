package com.example.restapilogin.remote;

public class ApiUtils {


    public static UserService getUserService(){
        return RetrofitClient.getClient().create(UserService.class);
    }
}