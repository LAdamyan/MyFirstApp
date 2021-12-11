package com.example.myfirstapp;
import java.util.ArrayList;
import java.util.List;

public class Comment {


    private String commentText;

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public static List<Comment> getCommentItems() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Comment1"));
        comments.add(new Comment("Comment2"));
        comments.add(new Comment("Comment3"));
        comments.add(new Comment("Comment4"));
        comments.add(new Comment("Comment5"));
        comments.add(new Comment("Comment6"));
        comments.add(new Comment("Comment7"));
        comments.add(new Comment("Comment8"));
        comments.add(new Comment("Comment1"));
        comments.add(new Comment("Comment2"));
        comments.add(new Comment("Comment3"));
        comments.add(new Comment("Comment4"));
        comments.add(new Comment("Comment5"));
        comments.add(new Comment("Comment6"));
        comments.add(new Comment("Comment7"));
        comments.add(new Comment("Comment8"));
        return comments;
    }

}