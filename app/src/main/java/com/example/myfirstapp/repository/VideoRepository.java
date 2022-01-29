package com.example.myfirstapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfirstapp.R;
import com.example.myfirstapp.Video.VideoProfile;
import com.example.myfirstapp.dto.SearchVideos;
import com.example.myfirstapp.dto.Video;
import com.example.myfirstapp.dto.VideoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoRepository {

    public Observable<List<VideoProfile>> getVideos(String param) {

        VideoService videoService = VideoService.create();

        return videoService.searchVideo(param)
                .observeOn(Schedulers.newThread())
                .map(searchVideos -> {
                    List<Video> videos = searchVideos.getVideos();

                    Stream<VideoProfile> videoProfileStream = videos.stream().map(video -> {
                        return new VideoProfile(video.getImage(), R.drawable.video_outline,
                                video.getVideoFiles().get(0).getLink());

                    });
                    return videoProfileStream.collect(Collectors.toList());


                });
    }
}