package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentsDao {

    @Query("SELECT * FROM comments ")
    List<Comments> getComments(int id);

    @Insert
    void insert(Comments comments);

    @Insert
    void insertAll(List<Comments> commentsList);

    @Delete
    void delete(Comments comments);


}
