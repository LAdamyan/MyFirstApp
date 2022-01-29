package com.example.myfirstapp.repository;

import com.example.myfirstapp.HomePage.HomePageProfile;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.ImageService;
import com.example.myfirstapp.dto.Photo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ImageRepository {

    public Observable<List<HomePageProfile>> getPhotos(String param) {

        ImageService imageService = ImageService.create();

        return imageService.searchImage(param)
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
                     return    new HomePageProfile(
                                R.drawable.world,
                                s1, s2,
                                photo.getSrc().getMediumUrl(),
                                0);


                    });
                    return  homePageProfileStream.collect(Collectors.toList());

                });
    }
}