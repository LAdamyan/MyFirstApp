package com.example.myfirstapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myfirstapp.Video.VideoProfile;
import com.example.myfirstapp.repository.VideoRepository;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class VideoViewModel extends ViewModel {

    private final VideoRepository videoRepository = new VideoRepository();

    public LiveData<List<VideoProfile>> getVideos(String param) {

        MutableLiveData<List<VideoProfile>> mutableLiveData = new MutableLiveData<>();

        videoRepository.getVideos(param)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<VideoProfile>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<VideoProfile> videoProfiles) {
                        mutableLiveData.postValue(videoProfiles);
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
