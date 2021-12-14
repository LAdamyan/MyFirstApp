package com.example.myfirstapp.Video;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.example.myfirstapp.R;

public class FullVideoFragment extends Fragment {

    VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_full_video, container, false);
        videoView = view.findViewById(R.id.video_view);
        loadVideo();
        return view;
    }

    private void loadVideo() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String videoUrl = bundle.getString("videoUrl");
            Uri uri = Uri.parse(videoUrl);
            videoView.setVideoURI(uri);
            MediaController mediaController = new MediaController(videoView.getContext());
            mediaController.setMediaPlayer(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            mediaController.show();

        }
    }



}