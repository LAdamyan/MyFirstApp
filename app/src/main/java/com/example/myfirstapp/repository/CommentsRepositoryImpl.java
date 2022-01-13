package com.example.myfirstapp.repository;
import com.example.myfirstapp.room.Comments;
import com.example.myfirstapp.room.CommentsDao;

import java.util.List;

public class CommentsRepositoryImpl implements CommentsRepository{

    private final CommentsDao commentsDao;

    public CommentsRepositoryImpl(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }


    @Override
    public List<Comments> getComments() {
        return commentsDao.getComments();
    }

    @Override
    public Comments getCommentsById() {
        return null;
    }

    @Override
    public void insertComments(Comments comments) {
      commentsDao.insert(comments);
    }

    @Override
    public void updateComments(Comments comments) {
      commentsDao.update(comments);
    }
}
