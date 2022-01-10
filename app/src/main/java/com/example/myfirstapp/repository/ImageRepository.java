package com.example.myfirstapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfirstapp.HomePage.HomePageProfile;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.Images;
import com.example.myfirstapp.dto.Photo;
import com.example.myfirstapp.dto.SearchPhotos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    public LiveData<List<HomePageProfile>> getPhotos(String param) {

        Images images = Images.create();

        MutableLiveData<List<HomePageProfile>> mutableLiveData = new MutableLiveData<>();

        images.searchImage(param).enqueue(new Callback<SearchPhotos>() {
            @Override
            public void onResponse(@NonNull Call<SearchPhotos> call, @NonNull Response<SearchPhotos> response) {
                SearchPhotos body = response.body();
                if (body != null) {
                    List<Photo> photos = body.getPhotos();

                    ArrayList<HomePageProfile> profilePhoto = new ArrayList<>();
                    for (Photo photo : photos) {
                        String[] s = photo.getPhotographer().split(" ");
                        String s1 = "";
                        String s2 = "";
                        if (s.length - 1 > 0) {
                            s1 = s[0];
                        }
                        if (s.length - 1 > 1) {
                            s2 = s[1];
                        }
                        profilePhoto.add(new HomePageProfile(
                                R.drawable.world,
                                s1, s2,
                                photo.getSrc().getMediumUrl(), 0));
                    }
                    mutableLiveData.setValue(profilePhoto);
                }
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
