package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DeviceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        int deviceId = getIntent().getIntExtra("device_id", -1);
    }
}