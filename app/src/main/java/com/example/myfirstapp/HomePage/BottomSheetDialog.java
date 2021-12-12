package com.example.myfirstapp.HomePage;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfirstapp.Comment;
import com.example.myfirstapp.CommentAdapter;
import com.example.myfirstapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {


   private static final String MY_PREF = "My preferences";

    CommentAdapter commentAdapter = new CommentAdapter();
    RecyclerView recycleView;
    AppCompatEditText editText ;
    AppCompatButton submitButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheetdialog,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView =view.findViewById(R.id.comments_recycle);
        editText = view.findViewById(R.id.dialog_edittext);
        submitButton= view.findViewById(R.id.comment_submit);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveComment();
                loadComment();
            }
        });

        initCommentRecycle();

    }

    private void initCommentRecycle(){
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycleView.setLayoutManager(linearLayoutManager2);
        recycleView.setAdapter(commentAdapter);
        commentAdapter.setComments(Comment.getCommentItems());
    }
    private void saveComment() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(editText!=null){
                editor.putString("comments", editText.getText().toString());
                editor.apply();
            }


        }
    }
    private void loadComment(){
        if (getActivity()!=null) {
            SharedPreferences sh = getActivity().getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
            String s1 = sh.getString("comments", "");

        }



    }






}
