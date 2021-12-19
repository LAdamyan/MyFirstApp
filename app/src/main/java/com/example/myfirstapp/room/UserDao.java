package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM usersHomePage")
    List<UsersHomePage> getUserHomePage();

    @Insert
    void insert(UsersHomePage homePage);

    @Delete
    void delete(UsersHomePage homePage);
}
