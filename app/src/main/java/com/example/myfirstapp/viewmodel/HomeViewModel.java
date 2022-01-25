package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myfirstapp.HomePage.HomePageProfile;
import com.example.myfirstapp.repository.ImageRepository;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private final ImageRepository imageRepository= new ImageRepository();

    public LiveData<List<HomePageProfile>> getPhotos(String param){


        MutableLiveData<List<HomePageProfile>> mutableLiveData = new MutableLiveData<>();

        imageRepository.getPhotos(param)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<HomePageProfile>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<HomePageProfile> homePageProfiles) {
                 mutableLiveData.postValue(homePageProfiles);
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
