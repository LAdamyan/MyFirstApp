package com.example.myfirstapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.myfirstapp.dto.Video;

import java.util.List;

import javax.xml.transform.sax.SAXResult;

@Entity (tableName = "users_home_page")
public class UsersHomePage {

    public UsersHomePage(int id, String imageUrl, String userName, String userSurName) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.userSurName = userSurName;
    }

    @PrimaryKey(autoGenerate = true)
    int id ;

    @ColumnInfo(name = "image_url")
    String imageUrl;

    @ColumnInfo(name = "username")
    String userName;

    @ColumnInfo(name = "user_surname")
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
