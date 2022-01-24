package com.example.myfirstapp.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.myfirstapp.R;
import com.example.myfirstapp.databinding.FragmentFullImageBinding;


public class FullImageFragment extends Fragment {

    private FragmentFullImageBinding fullImageBinding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fullImageBinding = FragmentFullImageBinding.inflate(inflater,container,false);
        load();
        return  fullImageBinding.getRoot();



    }
    private void load() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String imageUrl = bundle.getString("imageUrl");
            Glide.with(this).load(imageUrl).into(fullImageBinding.fullImage);

        }
    }

}