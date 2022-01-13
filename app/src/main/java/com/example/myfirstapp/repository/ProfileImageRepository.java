package com.example.myfirstapp.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myfirstapp.Image.Gallery;
import com.example.myfirstapp.dto.Images;
import com.example.myfirstapp.dto.Photo;
import com.example.myfirstapp.dto.SearchPhotos;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileImageRepository {

  public LiveData<List<Gallery>> getPhotos(String param) {

      Images images = Images.create();

      MutableLiveData<List<Gallery>>mutableLiveData = new MutableLiveData<>();

      images.searchImage(param).enqueue(new Callback<SearchPhotos>() {
          @Override
          public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
              SearchPhotos body = response.body();

              List<Gallery> imagePhoto = new ArrayList<>();
              if (body != null) {
                  List<Photo> photoList = body.getPhotos();

                  for (Photo photo : photoList) {
                      imagePhoto.add(new Gallery(photo.getSrc().getLargeUrl()));

                  }
                  mutableLiveData.setValue(imagePhoto);
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


