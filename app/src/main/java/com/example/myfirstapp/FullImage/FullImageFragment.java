package com.example.myfirstapp.FullImage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.myfirstapp.R;


public class FullImageFragment extends Fragment {

    AppCompatImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =   inflater.inflate(R.layout.fragment_full_image, container, false);
        imageView = view.findViewById(R.id.full_image);
        load();
        return  view;

    }
    private void load() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String imageUrl = bundle.getString("imageUrl");
            Glide.with(this).load(imageUrl).into(imageView);

        }
    }

}