package com.example.myfirstapp.Image;
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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfirstapp.HomePage.HomePageFragment;
import com.example.myfirstapp.InternetService;
import com.example.myfirstapp.R;
import com.example.myfirstapp.databinding.FragmentImageBinding;
import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.ProfileImage;
import com.example.myfirstapp.room.ProfileImageDao;
import com.example.myfirstapp.viewmodel.ProfileImageViewModel;
import java.util.ArrayList;
import java.util.List;



public class ImageFragment extends Fragment implements ItemClickListener2 {

    private  FragmentImageBinding fragmentImageBinding = null;

    ImageAdapter imageAdapter = new ImageAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentImageBinding = FragmentImageBinding.inflate(inflater,container,false);
        return fragmentImageBinding.getRoot();

    }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);


            fragmentImageBinding.profileSwipeRefresh.setOnRefreshListener(this::getImages);
            initRecycle(view);


            if (InternetService.isInternetConnected(getContext())) {
                getImages();

            } else {
                AppDatabase db = AppDatabase.getInstance(getContext());
                ProfileImageDao profileImageDao = db.getProfileImagesDao();
                List<ProfileImage> profileImageList = profileImageDao.getProfileImages();

                if (profileImageList.isEmpty()) {
                    showNoDataViews();}
                    else{
                    hideNoDataViews();
                    ArrayList<Gallery> profileGallery = new ArrayList<>();
                    for (ProfileImage profileImage : profileImageList) {
                        profileGallery.add(new Gallery(profileImage.getImageUrl()));

                    }
                    imageAdapter.setMyUrls(profileGallery);
                }


            }
        }
        private void getImages(){

            ProfileImageViewModel profileImageViewModel = ViewModelProviders.of(this).get(ProfileImageViewModel.class);

            profileImageViewModel.getPhotos("snow").observe(getViewLifecycleOwner(), new Observer<List<Gallery>>() {
                @Override
                public void onChanged(List<Gallery> galleries) {
                    ArrayList<Gallery> imagePhoto = new ArrayList<>();
                    imagePhoto.addAll(galleries);
                    imageAdapter.setMyUrls(imagePhoto);
                    fragmentImageBinding.profileSwipeRefresh.setRefreshing(false);
                    hideNoDataViews();
                }

            });
        }
    private void initRecycle(View view){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        fragmentImageBinding.recycleImageFragment.setLayoutManager(gridLayoutManager);
        fragmentImageBinding.recycleImageFragment.setAdapter(imageAdapter);
        imageAdapter.setItemClickListener2(this);
    }

    private void showNoDataViews() {
        fragmentImageBinding.recycleImageFragment.setVisibility(View.GONE);
        fragmentImageBinding.noDataGroup.setVisibility(View.VISIBLE);
    }

    private  void hideNoDataViews(){
        fragmentImageBinding.recycleImageFragment.setVisibility(View.VISIBLE);
        fragmentImageBinding.noDataGroup.setVisibility(View.GONE);
    }

    @Override
    public void onClick2(String imageUrl) {

        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        NavController navController = NavHostFragment.findNavController(ImageFragment.this);
        navController.navigate(R.id.action_imageFragment_to_fullImageFragment,bundle);



    }

}



