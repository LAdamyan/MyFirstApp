package com.example.myfirstapp.HomePage;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfirstapp.Image.FullImageFragment;
import com.example.myfirstapp.InternetService;
import com.example.myfirstapp.Profile.ProfileFragment;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.Images;
import com.example.myfirstapp.dto.Photo;
import com.example.myfirstapp.dto.SearchPhotos;
import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.UserDao;
import com.example.myfirstapp.room.UsersHomePage;
import com.example.myfirstapp.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment implements ItemClickListener {

    HomePageAdapter profilePageAdapter = new HomePageAdapter();
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    Group noDataGroups;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page_recycle, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        noDataGroups = view.findViewById(R.id.noDataGroup);

        swipeRefresh.setOnRefreshListener(this::getPhotos);

        initRecycle(view);

        if (InternetService.isInternetConnected(getContext())) {
            getPhotos();

        } else {
            AppDatabase db = AppDatabase.getInstance(getContext());
            UserDao userDao = db.getUsersDao();

            List<UsersHomePage> usersHomePageList = userDao.getUserHomePage();

            if(usersHomePageList.isEmpty()){
                showNoDataViews();
            }else{
                hideNoDataViews();
                ArrayList<HomePageProfile> homePageProfiles = new ArrayList<>();

                for (UsersHomePage usersHomePage : usersHomePageList) {
                    homePageProfiles.add(new HomePageProfile(R.drawable.world,
                            usersHomePage.getUserName(),
                            usersHomePage.getUserSurName(),
                            usersHomePage.getImageUrl(), 0));
                }
                profilePageAdapter.setProfiles(homePageProfiles);
            }
        }
    }
    private void getPhotos() {

        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        homeViewModel.getPhotos("ocean").observe(getViewLifecycleOwner(), homePageProfiles -> {

            if (homePageProfiles != null) {

                ArrayList<HomePageProfile> profilePhoto = new ArrayList<>();
                profilePhoto.addAll(homePageProfiles);
                profilePageAdapter.setProfiles(profilePhoto);
                saveToDb(profilePhoto);
                swipeRefresh.setRefreshing(false);
                hideNoDataViews();
            }
        });

    }

        private void showNoDataViews() {
        recyclerView.setVisibility(View.GONE);
        noDataGroups.setVisibility(View.VISIBLE);
    }

    private  void hideNoDataViews(){
        recyclerView.setVisibility(View.VISIBLE);
        noDataGroups.setVisibility(View.GONE);
    }


    private void saveToDb(ArrayList<HomePageProfile> profilePhoto) {
        AppDatabase db = AppDatabase.getInstance(getContext());
        UserDao userDao = db.getUsersDao();

        List<UsersHomePage> entity = new ArrayList<>();
        for (HomePageProfile dto : profilePhoto) {
            entity.add(new UsersHomePage(
                    0,
                    dto.getImageURL(),
                    dto.getName(),
                    dto.getSurName()
            ));
        }
        userDao.insertAll(entity);
    }

    private void initRecycle(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.home_page_recycleView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profilePageAdapter);
        profilePageAdapter.setItemClickListener(this);
    }


    @Override
    public void onClick(String name, String surname) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("surname", surname);
        profileFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity4_fragment_container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openFullImage(String imageUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        FullImageFragment fullImageFragment = new FullImageFragment();
        fullImageFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity4_fragment_container, fullImageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void OpenBottomDialog(int imageIcon) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getParentFragmentManager(), null);

    }

    @Override
    public void shareClick(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        Intent modeIntent = Intent.createChooser(intent,"Share with ...");
        startActivity(modeIntent);
    }



}