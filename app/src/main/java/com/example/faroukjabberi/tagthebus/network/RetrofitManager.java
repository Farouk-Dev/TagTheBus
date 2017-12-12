package com.example.faroukjabberi.tagthebus.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hadidi on 8/2/2017.
 * px
 */

public class RetrofitManager {
    private static RetrofitManager instance;
    private Retrofit retrofit;
    private RetrofitServices service;


    public static RetrofitManager getInstance(String baseUrl) {
        String url = baseUrl;
        if (instance == null || !instance.getRetrofit().baseUrl().equals(baseUrl)) {
            instance = initRetrofit(url);
        }
        return instance;
    }

       private static RetrofitManager initRetrofit(String baseUrl) {
        RetrofitManager retrofitManager = new RetrofitManager();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).build();
        retrofitManager.setRetrofit(new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build());
        RetrofitServices retrofitService = retrofitManager.getRetrofit().create(RetrofitServices.class);
        retrofitManager.setService(retrofitService);
        return retrofitManager;}





    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public RetrofitServices getService() {
        return service;
    }


    public void setService(RetrofitServices service) {
        this.service = service;
    }
}
