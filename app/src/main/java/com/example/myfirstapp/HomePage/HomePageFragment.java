package com.example.myfirstapp.HomePage;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfirstapp.FullImage.FullImageFragment;
import com.example.myfirstapp.Profile.ProfileFragment;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dto.Images;
import com.example.myfirstapp.dto.Photo;
import com.example.myfirstapp.dto.SearchPhotos;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment implements ItemClickListener {

    HomePageAdapter profilePageAdapter = new HomePageAdapter();
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page_recycle, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycle(view);


        Images images  = Images.create();
        Call<SearchPhotos> nature = images.searchImage("santa");

        nature.enqueue(new Callback<SearchPhotos>() {
            @Override
            public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
                SearchPhotos body = response.body();
                if (body != null) {
                    List<Photo> photos = body.getPhotos();

                    ArrayList<HomePageProfile> profilePhoto = new ArrayList<>();

                    for (Photo photo : photos) {

                        String[] s = photo.getPhotographer().split(" ");
                        String s1 = "";
                        String s2 = "";

                        if (s.length - 1 > 0) {
                            s1 = s[0];
                        }
                        if (s.length - 1 > 1) {
                            s2 = s[1];
                        }

                        profilePhoto.add(new HomePageProfile(
                                R.drawable.world,
                                s1, s2,
                                photo.getSrc().getMediumUrl(), 0));
                    }
                    profilePageAdapter.setProfiles(profilePhoto);
                }
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });


    }

    private void initRecycle(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.home_page_recycleView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profilePageAdapter);
        profilePageAdapter.setItemClickListener(this);
    }




    @Override
    public void onClick(String name, String surname) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("surname",surname);
        profileFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity4_fragment_container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openFullImage(String imageUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        FullImageFragment fullImageFragment = new FullImageFragment();
        fullImageFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity4_fragment_container, fullImageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void OpenBottomDialog(int imageIcon) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getParentFragmentManager(),null);


    }

    @Override
    public void shareClick(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(intent);
    }


}