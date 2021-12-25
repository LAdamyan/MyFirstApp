package com.example.myfirstapp;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.HomePage.HomePageProfile;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private final ArrayList<String>commentArrayList = new ArrayList<>();


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_recycle_items,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        String comment = commentArrayList.get(position);
        holder.initData(comment);

    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public void setComments(List<String> comments,int position){
        this.commentArrayList.clear();
        this.commentArrayList.addAll(comments);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }





}
 class CommentViewHolder extends RecyclerView.ViewHolder {

     AppCompatTextView textView = itemView.findViewById(R.id.comments);

     public CommentViewHolder(@NonNull View itemView) {
         super(itemView);
     }

     public void initData(String comment){
         textView.setText(comment);
     }

     }


