package com.example.myfirstapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_image")
public class ProfileImage {


    public ProfileImage(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "image_url")
    String imageUrl;

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
