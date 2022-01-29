package com.example.myfirstapp.retrofit;

import com.example.myfirstapp.dto.TokenInterceptor;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetup {

    private static final String BASE_URL = "https://api.pexels.com/";

    public  static Retrofit initRetrofit() {


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(getOkHttpClient());
        builder.addCallAdapterFactory(RxJava3CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(new TokenInterceptor());
        return okhttpBuilder.build();
    }

}



