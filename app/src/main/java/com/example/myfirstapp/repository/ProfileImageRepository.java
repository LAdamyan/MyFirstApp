package com.example.myfirstapp.repository;

import com.example.myfirstapp.Image.Gallery;
import com.example.myfirstapp.dto.ImageService;
import com.example.myfirstapp.dto.Photo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ProfileImageRepository {

  public Observable<List<Gallery>> getPhotos(String param) {

      ImageService imageService = ImageService.create();


      return imageService.searchImage(param)
              .observeOn(Schedulers.newThread())
              .map(searchPhotos -> {
                  List<Photo> photos = searchPhotos.getPhotos();

                  Stream<Gallery> profileStream = photos.stream().map(photo -> {
                      return  new Gallery(photo.getSrc().getLargeUrl());

                  });
                  return profileStream.collect(Collectors.toList());

              });

  }
}




