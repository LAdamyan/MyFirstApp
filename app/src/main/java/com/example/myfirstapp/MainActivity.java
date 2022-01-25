package com.example.myfirstapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.example.myfirstapp.HomePage.HomePageFragment;
import com.example.myfirstapp.broadcast.AirPlaneModeReceiver;
import com.example.myfirstapp.databinding.MainActivityBinding;


public class MainActivity extends AppCompatActivity {

    private MainActivityBinding mainActivityBinding = null;

    AirPlaneModeReceiver airPlaneModeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityBinding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());

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