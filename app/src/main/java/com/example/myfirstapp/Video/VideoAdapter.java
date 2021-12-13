package com.example.myfirstapp.Video;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfirstapp.FullVideo.FullVideoFragment;
import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private ArrayList<VideoImage> videoImages = new ArrayList<>();
    private onVideoClickListener onVideoClickListener;

    public void setVideoClickListener(onVideoClickListener videoClickListener) {
        this.onVideoClickListener = onVideoClickListener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_recycle_items, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoImage videoImage = videoImages.get(position);
        holder.initData(videoImage);
        holder.videoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVideoClickListener.openFullVideo(videoImage.getVideoUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoImages.size();
    }

    public void setMyVideos(List<VideoImage> videoImageList) {
        this.videoImages.clear();
        this.videoImages.addAll(videoImageList);
        notifyDataSetChanged();
    }

}

class VideoViewHolder extends RecyclerView.ViewHolder {

    AppCompatImageView videoImage;
    AppCompatImageView videoIcon;
    VideoView videoView;


    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        videoImage = itemView.findViewById(R.id.video_image);
        videoIcon = itemView.findViewById(R.id.video_icon);
        videoView = itemView.findViewById(R.id.video_view);
    }

    public void initData(VideoImage videoImage1) {

        Glide.with(itemView.getContext())
                .load(videoImage1.getVideoImageUrl())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop()
                .into(videoImage);
        videoIcon.setImageResource(videoImage1.getVideoIcon());
      //  videoView.setVideoURI(Uri.parse(videoImage1.getVideoUrl()));
    }
}

interface onVideoClickListener {
    void openFullVideo(String videoUrl);
}
