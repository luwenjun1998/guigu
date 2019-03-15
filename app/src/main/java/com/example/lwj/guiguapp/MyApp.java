package com.example.lwj.guiguapp;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10000L,TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);

     }
}
