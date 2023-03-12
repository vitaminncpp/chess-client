package com.akshayaap.chess.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private NetworkInterface api;
    private final Retrofit retrofit;


    public RetrofitClient(String uri) {
        retrofit = new Retrofit.Builder().baseUrl(uri).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(NetworkInterface.class);
    }

    public NetworkInterface getApi() {
        return api;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
