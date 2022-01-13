package com.example.myfirstapp.Video;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;;
import com.example.myfirstapp.InternetService;
import com.example.myfirstapp.R;
import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.ProfileVideo;
import com.example.myfirstapp.room.ProfileVideoDao;
import com.example.myfirstapp.viewmodel.VideoViewModel;
import java.util.ArrayList;
import java.util.List;


public class VideoFragment extends Fragment implements onVideoClickListener {

    private RecyclerView recyclerView;
    VideoAdapter videoAdapter = new VideoAdapter();
    SwipeRefreshLayout swipeRefreshLayout;
    Group noDataGroups;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_video, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.video_swipe_refresh);
        noDataGroups = view.findViewById(R.id.noDataGroup);
        swipeRefreshLayout.setOnRefreshListener(this::getVideos);

        initRecycle(view);

        if (InternetService.isInternetConnected(getContext())) {
            getVideos();

        }else {
            AppDatabase db = AppDatabase.getInstance(getContext());
            ProfileVideoDao profileVideoDao = db.getVideoDao();
            List<ProfileVideo> videoList = profileVideoDao.getVideos();

            if (videoList.isEmpty()) {
                showNoDataViews();}
            else{
                hideNoDataViews();
                ArrayList<VideoImage> videoImages = new ArrayList<>();

                for (ProfileVideo video : videoList) {
                    videoImages.add(new VideoImage(video.getVideoImage(),
                            R.drawable.video_outline, video.getVideoUrl()));

                }
                videoAdapter.setMyVideoImages(videoImages);
            }
            showNoDataViews();

        }
    }

    private void getVideos(){

       VideoViewModel videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        videoViewModel.getVideos("mountains").observe(getViewLifecycleOwner(), new Observer<List<VideoImage>>() {
            @Override
            public void onChanged(List<VideoImage> videoImages) {
                ArrayList<VideoImage> videoImage = new ArrayList<>();
                videoImage.addAll(videoImages);
                videoAdapter.setMyVideoImages(videoImage);
                swipeRefreshLayout.setRefreshing(false);
                hideNoDataViews();
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
    private void showNoDataViews() {
        recyclerView.setVisibility(View.GONE);
        noDataGroups.setVisibility(View.VISIBLE);
    }

    private  void hideNoDataViews(){
        recyclerView.setVisibility(View.VISIBLE);
        noDataGroups.setVisibility(View.GONE);
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