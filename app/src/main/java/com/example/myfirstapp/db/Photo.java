package com.example.myfirstapp.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {
    @SerializedName("id")
    int id;
    @SerializedName("photographer")
    String photographer;
    @SerializedName("src")
    Src src;


    public int getId() {
        return id;
    }

    public String getPhotographer() {
        return photographer;
    }

    public Src getSrc() {
        return src;
    }
}
