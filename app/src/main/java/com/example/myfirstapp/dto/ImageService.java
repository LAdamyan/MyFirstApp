package com.example.myfirstapp.dto;

import com.example.myfirstapp.retrofit.RetrofitSetup;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageService {



    @GET ("/v1/search")
    public Observable<SearchPhotos> searchImage(@Query("query")String param);

    static ImageService create(){
        return RetrofitSetup.initRetrofit().create(ImageService.class);
    }

}
