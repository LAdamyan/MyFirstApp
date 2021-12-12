package com.example.myfirstapp.Video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.FullImage.FullImageFragment;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.SearchVideos;
import com.example.myfirstapp.dto.Videos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoFragment extends Fragment implements onVideoClickListener {

    private RecyclerView recyclerView;
    VideoAdapter videoAdapter = new VideoAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_video, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycle(view);

        Videos videos = Videos.create();


        videos.searchVideo("nature" ).enqueue(new Callback<SearchVideos>() {
            @Override
            public void onResponse(Call<SearchVideos> call, Response<SearchVideos> response) {
                SearchVideos body = response.body();

                if (body != null) {
                    List<Videos> videos1 = body.getVideos();

                    ArrayList<VideoImage> videoImages = new ArrayList<>();

                 /*   for (Videos video : videos1) {
                        videoImages.add(new VideoImage(video.


                    }*/

                }
            }
            @Override
            public void onFailure(Call<SearchVideos> call, Throwable t) {

            }
        });



    }


    private void initRecycle(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.video_recycle);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setMyVideos(VideoImage.getVideos());
        videoAdapter.setVideoClickListener(this);
    }


    @Override
    public void openFullVideo(String videoUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("videoUrl", videoUrl);
        FullImageFragment fullVideoFragment = new FullImageFragment();
        fullVideoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity4_fragment_container, fullVideoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}