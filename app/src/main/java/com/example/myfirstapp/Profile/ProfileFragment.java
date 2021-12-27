package com.example.myfirstapp.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import com.bumptech.glide.Glide;
import com.example.myfirstapp.R;
import com.google.android.material.tabs.TabLayout;


public class ProfileFragment extends Fragment {

    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    AppCompatTextView name;
    AppCompatTextView surname;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.user_name);
        surname = view.findViewById(R.id.user_surname);



        tabLayout = view.findViewById(R.id.profile_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.image_multiple_outline));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.message_video));

        viewPager2 = view.findViewById(R.id.profile_viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getParentFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        load();
        return view;

    }

    private void load() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            name.setText(bundle.getString("name"));
            surname.setText(bundle.getString("surname"));

        }
    }
}