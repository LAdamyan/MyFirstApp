package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.myfirstapp.Video.VideoImage;
import com.example.myfirstapp.repository.VideoRepository;
import java.util.List;

public class VideoViewModel extends ViewModel {

    private final VideoRepository videoRepository = new VideoRepository();

    public LiveData<List<VideoImage>> getVideos(String param){

        LiveData<List<VideoImage>> videos= videoRepository.getVideos(param);
        return videos;
    }

}
