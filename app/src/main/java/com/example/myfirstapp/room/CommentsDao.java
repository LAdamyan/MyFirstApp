package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentsDao {

    @Query("SELECT * FROM comments WHERE post_id = :postId")

            List<Comments> getComments(int postId);

    @Insert
    void insert(Comments comments);





}
