package com.akshayaap.chess.network;


import com.akshayaap.chess.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface NetworkInterface {


    //User Operations
    @POST("/register")
    Call<User> register(@Body User user);

    @POST("/login")
    Call<User> login(@Body User login);
}
