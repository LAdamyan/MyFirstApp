package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfirstapp.HomePage.HomePageProfile;
import com.example.myfirstapp.repository.ImageRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private ImageRepository imageRepository= new ImageRepository();

    private MutableLiveData<List<HomePageProfile>> imageMutableLiveData = new MutableLiveData<>();
    public LiveData<List<HomePageProfile>>imagesLiveData = imageMutableLiveData;

    public void getPhotos(String param){
        MutableLiveData<List<HomePageProfile>> photos = imageRepository.getPhotos(param);
        imageMutableLiveData.setValue(photos.getValue());
    }
}
