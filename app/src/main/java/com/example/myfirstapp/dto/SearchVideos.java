package com.example.myfirstapp.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchVideos {

    @SerializedName("videos")
    List<Videos> videos;

    public List<Videos> getVideos() {
        return videos;
    }

}
