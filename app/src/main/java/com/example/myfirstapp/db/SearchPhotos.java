package com.example.myfirstapp.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchPhotos {

    @SerializedName("photos")
    List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }


}