package com.example.myfirstapp.dto;

import com.example.myfirstapp.retrofit.RetrofitSetup;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VideoService {


    @GET ("/videos/search")
    public Observable<SearchVideos> searchVideo(@Query("query")String param);

    static VideoService create(){
        return RetrofitSetup.initRetrofit().create(VideoService.class);
    }
}
