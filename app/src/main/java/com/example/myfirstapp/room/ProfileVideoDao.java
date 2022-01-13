package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfileVideoDao {

    @Query("SELECT * FROM ProfileVideo")
    List<ProfileVideo> getVideos();

    @Insert
    void insert(ProfileVideo profileVideo);

    @Insert
    void insertAll(List<ProfileVideo> profileVideos);

    @Delete
    void delete(ProfileVideo profileVideo);


}
