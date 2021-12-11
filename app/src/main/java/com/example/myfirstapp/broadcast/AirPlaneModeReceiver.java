package com.example.myfirstapp.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.myfirstapp.R;


public class AirPlaneModeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        boolean isAirPaleModeEnabled = intent.getBooleanExtra("state", false);
        if (isAirPaleModeEnabled) {

            View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();
            AppCompatButton button = view.findViewById(R.id.btn_ok);
            button.setOnClickListener(view1 -> dialog.dismiss());

        } else {
            Toast.makeText(context, "Air mode Is off", Toast.LENGTH_LONG).show();
        }
    }


}


