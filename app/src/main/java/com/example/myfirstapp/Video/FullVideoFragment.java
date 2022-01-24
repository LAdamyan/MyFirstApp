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
import com.example.myfirstapp.databinding.FragmentFullVideoBinding;

public class FullVideoFragment extends Fragment {

    private FragmentFullVideoBinding fullVideoBinding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       fullVideoBinding = FragmentFullVideoBinding.inflate(inflater,container,false);

        loadVideo();
        return fullVideoBinding.getRoot();
    }

    private void loadVideo() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String videoUrl = bundle.getString("videoUrl");
            Uri uri = Uri.parse(videoUrl);
            fullVideoBinding.videoView.setVideoURI(uri);
            fullVideoBinding.videoView.setVideoPath(videoUrl);
            MediaController mediaController = new MediaController(fullVideoBinding.videoView.getContext());
            mediaController.setAnchorView(fullVideoBinding.videoView);
            mediaController.setMediaPlayer(fullVideoBinding.videoView);
            fullVideoBinding.videoView.setMediaController(mediaController);
            fullVideoBinding.videoView.requestFocus();
            fullVideoBinding.videoView.start();
            mediaController.show();



        }
    }



}