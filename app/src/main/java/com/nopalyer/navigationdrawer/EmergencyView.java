package com.nopalyer.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class EmergencyView extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
    }
    public void startService(View v) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }
}