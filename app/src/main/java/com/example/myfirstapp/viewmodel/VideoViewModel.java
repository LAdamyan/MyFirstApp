package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.myfirstapp.Video.VideoProfile;
import com.example.myfirstapp.repository.VideoRepository;
import java.util.List;

public class VideoViewModel extends ViewModel {

    private final VideoRepository videoRepository = new VideoRepository();

    public LiveData<List<VideoProfile>> getVideos(String param){

        LiveData<List<VideoProfile>> videos= videoRepository.getVideos(param);
        return videos;
    }

}
