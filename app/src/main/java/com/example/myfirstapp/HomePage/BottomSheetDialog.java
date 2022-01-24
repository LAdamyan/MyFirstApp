package com.example.myfirstapp.HomePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfirstapp.CommentAdapter;
import com.example.myfirstapp.R;
import com.example.myfirstapp.databinding.BottomsheetdialogBinding;
import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.Comments;
import com.example.myfirstapp.room.CommentsDao;
import com.example.myfirstapp.viewmodel.BottomSheetDialogViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.List;

;

public class BottomSheetDialog extends BottomSheetDialogFragment {


    private  BottomsheetdialogBinding viewBinding = null;

    private static final String POST_ID = "post_id";
    CommentAdapter commentAdapter = new CommentAdapter();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewBinding= BottomsheetdialogBinding.inflate(inflater,container,false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCommentRecycle();

        Bundle args = getArguments();
        if (args != null) {
            int postId = args.getInt(POST_ID, 0);

            loadComments(postId);
            viewBinding.commentSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String comment = viewBinding.dialogEdittext.getText().toString();
                    saveComment(comment, postId);

                }

            });
        }

 }


    private void initCommentRecycle() {
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        viewBinding.commentsRecycle.setLayoutManager(linearLayoutManager2);
        viewBinding.commentsRecycle.setAdapter(commentAdapter);

    }

    private void loadComments(int id){
        AppDatabase db = AppDatabase.getInstance(getContext());
        CommentsDao commentsDao = db.getCommentsDao();
        List<Comments> commentsList = commentsDao.getComments();
        ArrayList<String> commentsArrayList = new ArrayList<>();
        for (Comments comment : commentsList) {
            commentsArrayList.add(comment.getCommentsText());
        }
        commentAdapter.setComments(commentsArrayList);

    }


    private void saveComment(String commentText,int postId) {

        BottomSheetDialogViewModel bottomSheetDialogViewModel = ViewModelProviders.of(this).
                get(BottomSheetDialogViewModel.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Comments newComment = new Comments();
                newComment.setPostId(postId);
                newComment.setCommentsText(commentText);
                bottomSheetDialogViewModel.insertComment(newComment);
            }
        }).start();
        dismiss();

    }


}