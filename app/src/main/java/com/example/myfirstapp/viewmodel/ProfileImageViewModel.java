package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.myfirstapp.Image.Gallery;
import com.example.myfirstapp.repository.ProfileImageRepository;
import java.util.List;

public class ProfileImageViewModel extends ViewModel {

    private final ProfileImageRepository profileImageRepository = new ProfileImageRepository();

    public LiveData<List<Gallery>> getPhotos(String param){

        LiveData<List<Gallery>> photos= profileImageRepository.getPhotos(param);
        return photos;
    }
}
