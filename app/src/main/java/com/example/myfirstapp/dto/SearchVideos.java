package com.example.myfirstapp.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchVideos {

    @SerializedName("videos")
    List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

}
