package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommentsDao {

    @Query("SELECT * FROM comments")
    List<Comments> getComments();

    @Insert
    void insert(Comments comments);

    @Update
    void update(Comments comments);





}
