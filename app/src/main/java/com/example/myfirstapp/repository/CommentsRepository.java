package com.example.myfirstapp.repository;
import com.example.myfirstapp.room.Comments;
import java.util.List;

interface CommentsRepository {

    List<Comments> getComments();

    Comments getCommentsById();

    void insertComments(Comments comments);

    void updateComments(Comments comments);

}
