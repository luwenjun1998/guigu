package com.example.lwj.guiguapp.home.server;

import com.example.lwj.guiguapp.home.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface HomeServer {
    @GET("{path}")
    Observable<HomeBean> getData(@Path("path")String path);
}
