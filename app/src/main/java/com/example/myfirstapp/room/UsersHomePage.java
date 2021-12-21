package com.example.myfirstapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.myfirstapp.dto.Video;

import java.util.List;

import javax.xml.transform.sax.SAXResult;

@Entity (tableName = "usersHomePage")
public class UsersHomePage {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "imageUrl")
    String imageUrl;

    @ColumnInfo(name = "userName")
    String userName;

    @ColumnInfo(name = "userSurName")
    String userSurName;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }
}
