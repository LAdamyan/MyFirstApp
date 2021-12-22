package com.example.myfirstapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import com.example.myfirstapp.HomePage.HomePageFragment;
import com.example.myfirstapp.broadcast.AirPlaneModeReceiver;

import com.example.myfirstapp.room.AppDatabase;
import com.example.myfirstapp.room.UserDao;
import com.example.myfirstapp.room.UsersHomePage;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    AirPlaneModeReceiver airPlaneModeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        HomePageFragment homePageFragment = new HomePageFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity4_fragment_container, homePageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        airPlaneModeReceiver = new AirPlaneModeReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airPlaneModeReceiver, intentFilter);





    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airPlaneModeReceiver);
    }

}