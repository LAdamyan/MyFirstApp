package com.example.myfirstapp.room;

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

    @TypeConverters(StringConverter.class)
    String imageUrl;
    String userName;
    String userSurName;




}
