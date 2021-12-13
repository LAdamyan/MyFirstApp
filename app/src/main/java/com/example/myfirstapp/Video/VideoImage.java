package com.example.myfirstapp.Video;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoImage {

    private String videoImageUrl;
    private int videoIcon;
    private String videoUrl;

    public VideoImage(String videoImageUrl, int videoIcon,String videoUrl) {
        this.videoImageUrl = videoImageUrl;
        this.videoIcon = videoIcon;
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public int getVideoIcon() {
        return videoIcon;
    }

    public static List<VideoImage>getVideos(){
        ArrayList<VideoImage> videoImages = new ArrayList<>();

        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));
        videoImages.add(new VideoImage("https://static-videos.pexels.com/videos/1093662/pictures/preview-0.jpg",
                R.drawable.video_outline,"https://pin.it/5DM9lrv" ));



        return videoImages;
    }
}
