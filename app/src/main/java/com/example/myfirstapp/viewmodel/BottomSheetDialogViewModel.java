package com.example.myfirstapp.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.myfirstapp.repository.CommentsRepositoryImpl;
import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.Comments;

public class BottomSheetDialogViewModel extends AndroidViewModel {

    private  final  AppDatabase appDatabase;


    public BottomSheetDialogViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(application.getApplicationContext());

    }

    public void insertComment(Comments comments){
        if(appDatabase!=null){
            CommentsRepositoryImpl commentsRepository = new CommentsRepositoryImpl
                                                   (appDatabase.getCommentsDao());
            commentsRepository.insertComments(comments);
        }
    }
}
