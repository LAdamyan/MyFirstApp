package com.example.myfirstapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfileVideo {

    public ProfileVideo(int id, int icon, String videoImage, String videoUrl) {
        this.id = id;
        this.icon = icon;
        this.videoImage = videoImage;
        this.videoUrl = videoUrl;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "icon")
    int icon;

    @ColumnInfo(name = "video_image")
    String videoImage;

    @ColumnInfo(name = "video_url")
    String videoUrl;

    public int getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
