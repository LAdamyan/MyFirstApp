package com.example.myfirstapp.HomePage;
import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.CommentAdapter;
import com.example.myfirstapp.R;
import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.Comments;
import com.example.myfirstapp.room.CommentsDao;
import com.example.myfirstapp.room.UserDao;
import com.example.myfirstapp.room.UsersHomePage;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialog extends BottomSheetDialogFragment {


    private static final String MY_PREF = "My preferences";

    CommentAdapter commentAdapter = new CommentAdapter();
    RecyclerView recycleView;
    AppCompatEditText editText;
    AppCompatButton submitButton;
    List<String> commentArrayList= new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheetdialog, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView = view.findViewById(R.id.comments_recycle);
        editText = view.findViewById(R.id.dialog_edittext);
        submitButton = view.findViewById(R.id.comment_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment1 = editText.getText().toString();
                saveComment(comment1);

            }
        });

        initCommentRecycle();
        loadComment();

    }


    private void initCommentRecycle() {
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(linearLayoutManager2);
        recycleView.setAdapter(commentAdapter);

    }

    private void getCommentsById(int id){
        AppDatabase db = AppDatabase.getInstance(getContext());
        CommentsDao commentsDao = db.getCommentsDao();

        List<Comments> commentsList = commentsDao.getComments(id);
        ArrayList<Comments> comments1 = new ArrayList<>();

        for (Comments comments : commentsList) {
            comments1.add(new Comments(comments.getId(),
                    comments.getImageUrl(),
                    comments.getComments()));

        }


    }


    private void saveComment(String comment) {
        if (getActivity() != null) {
            commentArrayList.add(comment);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MY_PREF, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            Gson gson = new Gson();
            String jsonData = sharedPreferences.getString("comments", null);
            Type type = new TypeToken<List<String>>() {}.getType();

            commentArrayList.clear();
            List<String> comments = gson.fromJson(jsonData,type);
            commentArrayList.addAll(comments);
            commentArrayList.add(comment);

            String json = gson.toJson(commentArrayList);
            editor.putString("comments", json);
            editor.apply();
            Toast.makeText(getContext(),"The comment has saved successfully!!",Toast.LENGTH_LONG).show();
            dismiss();

        }
    }
    private void loadComment() {

        if (getActivity() != null) {
            SharedPreferences sh = getActivity().getSharedPreferences(MY_PREF, MODE_PRIVATE);
            Gson gson = new Gson();
            String jsonData = sh.getString("comments", null);
            Type type = new TypeToken<List<String>>() {}.getType();
            if(gson.fromJson(jsonData, type)!= null) {
                commentArrayList.addAll(gson.fromJson(jsonData, type));
                commentAdapter.setComments(commentArrayList);

        }
    }
    }
}