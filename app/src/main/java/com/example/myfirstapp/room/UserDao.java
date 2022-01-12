package com.example.myfirstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface UserDao {

@Transaction
    @Query("SELECT * FROM users_home_page")
    List<UsersHomePage> getUserHomePage();

    @Insert
    void insert(UsersHomePage homePage);

    @Insert
    void insertAll(List<UsersHomePage> posts);

    @Delete
    void delete(UsersHomePage homePage);
}
