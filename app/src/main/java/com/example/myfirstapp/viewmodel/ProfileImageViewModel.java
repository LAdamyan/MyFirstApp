package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfirstapp.HomePage.HomePageProfile;
import com.example.myfirstapp.Image.Gallery;
import com.example.myfirstapp.repository.ProfileImageRepository;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileImageViewModel extends ViewModel {

    private final ProfileImageRepository profileImageRepository = new ProfileImageRepository();

    public LiveData<List<Gallery>> getPhotos(String param){

        MutableLiveData<List<Gallery>> mutableLiveData = new MutableLiveData<>();

        profileImageRepository.getPhotos(param)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Gallery>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Gallery> galleries) {
                       mutableLiveData.postValue(galleries);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mutableLiveData;
    }
}
