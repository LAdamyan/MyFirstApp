package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfileImageDao {

    @Query("SELECT * FROM profile_image")
    List<ProfileImage> getProfileImages();


    @Insert
    void insert(ProfileImage profileImage);

    @Insert
    void insertAll(List<ProfileImage> profileImages);

    @Delete
    void delete (ProfileImage profileImage);
}
