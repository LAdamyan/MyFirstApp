package com.example.myfirstapp.Video;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoImage {

    private String videoImageUrl;
    private int videoIcon;

    public VideoImage(String videoImageUrl, int videoIcon) {
        this.videoImageUrl = videoImageUrl;
        this.videoIcon = videoIcon;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public int getVideoIcon() {
        return videoIcon;
    }

    public static List<VideoImage>getVideos(){
        ArrayList<VideoImage> videoImages = new ArrayList<>();
        videoImages.add(new VideoImage("https://pin.it/3v9zO4W", R.drawable.video_outline));
        videoImages.add(new VideoImage("https://pin.it/3v9zO4W", R.drawable.video_outline));
        videoImages.add(new VideoImage("https://pin.it/3v9zO4W", R.drawable.video_outline));
        videoImages.add(new VideoImage("https://pin.it/3v9zO4W", R.drawable.video_outline));
        videoImages.add(new VideoImage("https://pin.it/3v9zO4W", R.drawable.video_outline));
        videoImages.add(new VideoImage("https://pin.it/3v9zO4W", R.drawable.video_outline));


        return videoImages;
    }
}
