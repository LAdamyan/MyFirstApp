package com.example.myfirstapp.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.myfirstapp.HomePage.HomePageProfile;

import java.util.List;

public class CommentsAndUsersHomePage {

    @Embedded UsersHomePage usersHomePage;

    @Relation(
            parentColumn = "id",
            entityColumn = "post_id")

    public List<Comments> comments;
}
