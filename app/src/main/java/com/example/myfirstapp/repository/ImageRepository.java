package com.example.myfirstapp.repository;
import android.media.Image;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ImageRepository {

    public Observable<List<HomePageProfile>> getPhotos(String param) {

        Images images = Images.create();

        return images.searchImage(param)
                .observeOn(Schedulers.newThread())
                .map(searchPhotos -> {
                    List<Photo> photos = searchPhotos.getPhotos();

                    Stream<HomePageProfile> homePageProfileStream = photos.stream().map(photo -> {

                        String[] s = photo.getPhotographer().split(" ");
                        String s1 = "";
                        String s2 = "";
                        if (s.length - 1 > 0) {
                            s1 = s[0];
                        }
                        if (s.length - 1 > 1) {
                            s2 = s[1];
                        };
                        new HomePageProfile(
                                R.drawable.world,
                                s1, s2,
                                photo.getSrc().getMediumUrl(),
                                0);

                        return null;
                    });
                    return  homePageProfileStream.collect(Collectors.toList());

                });
    }
}