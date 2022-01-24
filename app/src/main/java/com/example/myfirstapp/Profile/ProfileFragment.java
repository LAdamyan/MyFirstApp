package com.example.myfirstapp.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;


import com.bumptech.glide.Glide;
import com.example.myfirstapp.HomePage.HomePageFragment;
import com.example.myfirstapp.R;
import com.example.myfirstapp.databinding.FragmentProfileBinding;
import com.google.android.material.tabs.TabLayout;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding profileBinding = null;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileBinding = FragmentProfileBinding.inflate(inflater,container,false);


        profileBinding.profileTabLayout.addTab(profileBinding.profileTabLayout.newTab().setIcon(R.drawable.image_multiple_outline));
        profileBinding.profileTabLayout.addTab(profileBinding.profileTabLayout.newTab().setIcon(R.drawable.message_video));

        viewPagerAdapter = new ViewPagerAdapter(getParentFragmentManager(), getLifecycle());
        profileBinding.profileViewPager.setAdapter(viewPagerAdapter);

        profileBinding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(ProfileFragment.this);
                navController.navigate(R.id.action_profileFragment_to_homePageFragment);
            }
        });



        profileBinding.profileTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                profileBinding.profileViewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        load();
        return profileBinding.getRoot();

    }


    private void load() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            profileBinding.userName.setText(bundle.getString("name"));
            profileBinding.userSurname.setText(bundle.getString("surname"));

        }
    }
}