package com.example.myfirstapp.Image;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.myfirstapp.HomePage.NoDataFragment;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.Images;
import com.example.myfirstapp.dto.Photo;
import com.example.myfirstapp.dto.SearchPhotos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageFragment extends Fragment implements ItemClickListener2 {

    ImageAdapter imageAdapter = new ImageAdapter();
    SwipeRefreshLayout swipeRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);

    }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            swipeRefresh = view.findViewById(R.id.profile_swipe_refresh);

            swipeRefresh.setOnRefreshListener(this::getImages);
            initRecycle(view);

        }

        private void getImages(){
            Images images = Images.create();
            Call<SearchPhotos> rose = images.searchImage("christmas tree");
            rose.enqueue(new Callback<SearchPhotos>() {
                @Override
                public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
                    SearchPhotos body = response.body();
                    List<Photo> photoList = body.getPhotos();

                    ArrayList<Gallery>imagePhoto = new ArrayList<>();
                    for (Photo photo: photoList) {
                        imagePhoto.add(new Gallery(photo.getSrc().getLargeUrl()));

                    }
                    imageAdapter.setMyUrls(imagePhoto);
                    swipeRefresh.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<SearchPhotos> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                    swipeRefresh.setRefreshing(false);
                }
            });
        }

    private void initRecycle(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycle_image_fragment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setItemClickListener2(this);
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
    public void onClick2(String imageUrl) {

        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        FullImageFragment fullImageFragment = new FullImageFragment();
        fullImageFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity4_fragment_container, fullImageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }

}



