package com.example.myfirstapp.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity (tableName = "comments")
public class Comments {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "post_id")
    int postId;

    @ColumnInfo(name = "commentsText")
    String commentsText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentsText() {
        return commentsText;
    }

    public void setCommentsText(String commentsText) {
        this.commentsText = commentsText;
    }
}
