package com.example.myfirstapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfirstapp.R;
import com.example.myfirstapp.Video.VideoProfile;
import com.example.myfirstapp.dto.SearchVideos;
import com.example.myfirstapp.dto.Video;
import com.example.myfirstapp.dto.Videos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoRepository {

    public LiveData<List<VideoProfile>> getVideos(String param) {

        Videos videos = Videos.create();

        MutableLiveData<List<VideoProfile>> mutableLiveData = new MutableLiveData<>();

        videos.searchVideo(param).enqueue(new Callback<SearchVideos>() {
            @Override
            public void onResponse(Call<SearchVideos> call, Response<SearchVideos> response) {
                SearchVideos body = response.body();

                if (body != null) {
                    List<Video> videoList = body.getVideos();
                    ArrayList<VideoProfile> imageVideo = new ArrayList<>();

                    for (Video video : videoList) {
                        imageVideo.add(new VideoProfile(
                                video.getImage(), R.drawable.video_outline,
                                video.getVideoFiles().get(0).getLink()));

                    }
                    mutableLiveData.setValue(imageVideo);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchVideos> call, @NonNull Throwable t) {
                  mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}