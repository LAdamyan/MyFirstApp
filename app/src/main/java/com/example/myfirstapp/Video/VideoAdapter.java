package com.example.myfirstapp.Video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private ArrayList<VideoProfile> videoProfiles = new ArrayList<>();
    private onVideoClickListener onVideoClickListener;


    public void setVideoClickListener(onVideoClickListener onVideoClickListener) {
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
        VideoProfile videoProfile = videoProfiles.get(position);
        holder.initData(videoProfile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVideoClickListener.openFullVideo(videoProfile.getVideoUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoProfiles.size();
    }

    public void setMyVideoImages(List<VideoProfile> videoProfileList) {
        this.videoProfiles.clear();
        this.videoProfiles.addAll(videoProfileList);
        notifyDataSetChanged();
    }

}

class VideoViewHolder extends RecyclerView.ViewHolder {

    AppCompatImageView videoImage;
    AppCompatImageView videoIcon;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        videoImage = itemView.findViewById(R.id.video_image);
        videoIcon = itemView.findViewById(R.id.video_icon);

    }

    public void initData(VideoProfile videoProfile1) {

        Glide.with(itemView.getContext())
                .load(videoProfile1.getVideoImageUrl())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop()
                .into(videoImage);
        videoIcon.setImageResource(videoProfile1.getVideoIcon());

    }
}

interface onVideoClickListener {
    void openFullVideo(String videoImageUrl);
}
