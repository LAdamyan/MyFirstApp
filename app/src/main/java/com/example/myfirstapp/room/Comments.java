package com.example.myfirstapp.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity (tableName = "comments")
public class Comments {

    public Comments(int id, String imageUrl, String comments) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.comments = comments;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "imageUrl")
    String imageUrl;

    @ColumnInfo(name = "comments")
    String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
