package com.project.appcv;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.13:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
