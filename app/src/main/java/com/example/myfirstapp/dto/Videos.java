package com.example.myfirstapp.dto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Videos {


    @GET ("/video/search")
    Call<SearchVideos>searchVideo(@Query("query")String param);

    static Videos create(){
        return RetrofitSetup.initRetrofit().create(Videos.class);
    }
}
