package com.project.appcv;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS) // Thiết lập thời gian timeout kết nối (15 giây)
                .readTimeout(15, TimeUnit.SECONDS) // Thiết lập thời gian timeout đọc dữ liệu (15 giây)
                .build();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.13:8080")
                    .client(okHttpClient) // Sử dụng OkHttpClient tùy chỉnh
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
