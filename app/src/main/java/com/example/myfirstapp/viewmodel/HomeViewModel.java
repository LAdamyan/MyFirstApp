package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfirstapp.HomePage.HomePageProfile;
import com.example.myfirstapp.repository.ImageRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private ImageRepository imageRepository= new ImageRepository();

    public LiveData<List<HomePageProfile>> getPhotos(String param){
        LiveData<List<HomePageProfile>> photos = imageRepository.getPhotos(param);
        return photos;
    }
}
