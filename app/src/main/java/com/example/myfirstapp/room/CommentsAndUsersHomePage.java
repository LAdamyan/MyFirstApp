package com.example.myfirstapp.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.myfirstapp.HomePage.HomePageProfile;

public class CommentsAndUsersHomePage {

    @Embedded Comments comments;

    @Relation(
            parentColumn = "post_id",
            entityColumn = "image_url")

    public HomePageProfile homePageProfile;
}
