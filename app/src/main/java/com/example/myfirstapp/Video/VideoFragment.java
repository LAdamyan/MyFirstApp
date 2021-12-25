package com.example.myfirstapp.Video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfirstapp.HomePage.NoDataFragment;
import com.example.myfirstapp.Image.Gallery;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.Photo;
import com.example.myfirstapp.dto.SearchPhotos;
import com.example.myfirstapp.dto.SearchVideos;
import com.example.myfirstapp.dto.Video;
import com.example.myfirstapp.dto.VideoFiles;
import com.example.myfirstapp.dto.Videos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoFragment extends Fragment implements onVideoClickListener {

    private RecyclerView recyclerView;
    VideoAdapter videoAdapter = new VideoAdapter();
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_video, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.video_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this::getVideos);

        initRecycle(view);

    }

    private void getVideos(){
        Videos videos = Videos.create();
        videos.searchVideo("New Year Eve" ).enqueue(new Callback<SearchVideos>() {
            @Override
            public void onResponse(Call<SearchVideos> call, Response<SearchVideos> response) {

                SearchVideos body = response.body();
                List<Video> videoList = body.getVideos();

                ArrayList<VideoImage>videoImages = new ArrayList<>();

                for (Video video : videoList) {
                    videoImages.add(new VideoImage(video.getImage(),
                            R.drawable.video_outline,video.getVideoFiles().get(0).getLink()));

                }
                videoAdapter.setMyVideoImages(videoImages);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<SearchVideos> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }


    private void initRecycle(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.video_recycle);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setVideoClickListener(this);
    }

    private void noDataFound(){
        NoDataFragment noDataFragment = new NoDataFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity4_fragment_container,noDataFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void openFullVideo(String videoUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("videoUrl", videoUrl);
        FullVideoFragment fullVideoFragment = new FullVideoFragment();
        fullVideoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity4_fragment_container, fullVideoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}