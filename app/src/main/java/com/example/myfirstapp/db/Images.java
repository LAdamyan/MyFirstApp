package com.example.myfirstapp.db;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Images {


    @GET ("/v1/search")
    public Call<SearchPhotos>searchImage(@Query("query")String param);

}
