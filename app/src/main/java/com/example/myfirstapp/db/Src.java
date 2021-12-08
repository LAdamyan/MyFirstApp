package com.example.myfirstapp.db;

import com.google.gson.annotations.SerializedName;

public class Src {
    @SerializedName("medUrl")
    String mediumUrl;

    public String getMediumUrl() {
        return mediumUrl;
    }
}
