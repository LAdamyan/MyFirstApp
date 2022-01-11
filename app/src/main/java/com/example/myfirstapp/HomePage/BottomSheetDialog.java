package com.example.myfirstapp.HomePage;
;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialog extends BottomSheetDialogFragment {


    private static final String POST_ID = "post_id";
    CommentAdapter commentAdapter = new CommentAdapter();
    RecyclerView recycleView;
    AppCompatEditText editText;
    AppCompatButton submitButton;


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

        initCommentRecycle();

        Bundle args = getArguments();
        if (args != null) {
            int postId = args.getInt(POST_ID, 0);
            loadComments(postId);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String comment = editText.getText().toString();
                    saveComment(comment, postId);
                    dismiss();

                }

            });
        }

 }


    private void initCommentRecycle() {
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(linearLayoutManager2);
        recycleView.setAdapter(commentAdapter);

    }

    private void loadComments(int id){
        AppDatabase db = AppDatabase.getInstance(getContext());
        CommentsDao commentsDao = db.getCommentsDao();
        List<Comments> commentsList = commentsDao.getComments(id);
        ArrayList<String> commentsArrayList = new ArrayList<>();
        for (Comments comment : commentsList) {
            commentsArrayList.add(comment.getCommentsText());
        }
        commentAdapter.setComments(commentsArrayList);

    }


    private void saveComment(String commentText,int postId) {

        AppDatabase db = AppDatabase.getInstance(getContext());
        CommentsDao commentsDao = db.getCommentsDao();
        Comments newComment = new Comments();
        newComment.setPostId(postId);
        newComment.setCommentsText(commentText);
        commentsDao.insert(newComment);
        dismiss();

        }


}