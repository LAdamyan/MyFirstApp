package com.example.myfirstapp.HomePage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfirstapp.FullImage.FullImageFragment;
import com.example.myfirstapp.Profile.ProfileFragment;
import com.example.myfirstapp.R;
import com.example.myfirstapp.db.Images;
import com.example.myfirstapp.db.Photo;
import com.example.myfirstapp.db.RetrofitSetup;
import com.example.myfirstapp.db.SearchPhotos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment implements ItemClickListener {

    HomePageAdapter profilePageAdapter = new HomePageAdapter();
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_recycle, container, false);
        recyclerView = view.findViewById(R.id.home_page_recycle);

        initRecycle();


        RetrofitSetup retrofitSetup = new RetrofitSetup();
        Images images = retrofitSetup.initRetrofit();
        Call<SearchPhotos> nature = images.searchImage("nature");
         nature.enqueue(new Callback<SearchPhotos>() {
             @Override
             public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
                 SearchPhotos body = response.body();
                List<Photo>photos =  body.getPhotos();

                ArrayList<HomePageProfile> profilePhoto = new ArrayList<>();



                 for (Photo photo: photos) {

                     String[] s = photo.getPhotographer().split(" ");
                     String s1 = s[0];
                     String s2 = s[0];

                     profilePhoto.add(new HomePageProfile(
                             R.drawable.world,
                             s1,s2,
                             photo.getSrc().getLargeUrl(),0));
                 }
                 profilePageAdapter.setProfiles(profilePhoto);
             }

             @Override
             public void onFailure(Call<SearchPhotos> call, Throwable t) {

             }
         });



        return view;

    }
    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profilePageAdapter);


        profilePageAdapter.setItemClickListener(this);
    }

    @Override
    public void onClick(String name, String surname) {
        ProfileFragment profileFragment = new ProfileFragment();
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
}