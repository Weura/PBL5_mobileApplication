package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeviceDetailsActivity extends AppCompatActivity {

    private TextView deviceNameTextView;

    private TextView totalUsesTextView;
    private TextView freeUsesTextView;

    private TextView dailyUsesTextView;
    private TextView dailyUsesLeftTextView;
    private TextView weeklyUsesTextView;
    private TextView weeklyUsesLeftTextView;
    private TextView monthlyUsesTextView;
    private TextView monthlyUsesLeftTextView;

    private Button returnButton;
    private Button tempChartButton;
    private Button humidityChartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);

        deviceNameTextView = findViewById(R.id.details_device_name);

        totalUsesTextView = findViewById(R.id.details_device_total_uses);
        freeUsesTextView = findViewById(R.id.details_device_free_uses);

        dailyUsesTextView = findViewById(R.id.details_device_daily_uses);
        dailyUsesLeftTextView = findViewById(R.id.details_device_daily_uses_left);
        weeklyUsesTextView = findViewById(R.id.details_device_weekly_uses);
        weeklyUsesLeftTextView = findViewById(R.id.details_device_weekly_uses_left);
        monthlyUsesTextView = findViewById(R.id.details_device_monthly_uses);
        monthlyUsesLeftTextView = findViewById(R.id.details_device_monthly_uses_left);

//      Values from intent
        Intent intent = getIntent();
        int deviceId = intent.getIntExtra("device_id", 0);

        String deviceName = intent.getStringExtra("device_name");

        int totalUses = intent.getIntExtra("total_uses", 0);
        int freeUses = intent.getIntExtra("free_uses", 0);

        int dailyUses = intent.getIntExtra("daily_uses", 0);
        int dailyUsesLeft = intent.getIntExtra("daily_uses_left", 0);
        int weeklyUses = intent.getIntExtra("weekly_uses", 0);
        int weeklyUsesLeft = intent.getIntExtra("weekly_uses_left", 0);
        int monthlyUses = intent.getIntExtra("monthly_uses", 0);
        int monthlyUsesLeft = intent.getIntExtra("monthly_uses_left", 0);

//       Setting TextViews accordingly
        deviceNameTextView.setText(deviceName);

        totalUsesTextView.setText("Total uses: " + String.valueOf(totalUses));
        freeUsesTextView.setText("Free uses: " + String.valueOf(freeUses));

        dailyUsesTextView.setText(String.valueOf(dailyUses));
        dailyUsesLeftTextView.setText(String.valueOf(dailyUsesLeft));
        weeklyUsesTextView.setText(String.valueOf(weeklyUses));
        weeklyUsesLeftTextView.setText(String.valueOf(weeklyUsesLeft));
        monthlyUsesTextView.setText(String.valueOf(monthlyUses));
        monthlyUsesLeftTextView.setText(String.valueOf(monthlyUsesLeft));

//        navigate to main scrolling activity
        returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(view -> {
            Intent newIntent = new Intent(DeviceDetailsActivity.this, MainScrollingActivity.class);
            startActivity(newIntent);
            finish();
        });

//        navigate to temperature chart activity
        tempChartButton = findViewById(R.id.see_temp_button);
        tempChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceDetailsActivity.this, TemperatureChartActivity.class);
                intent.putExtra("device_id", deviceId);
                intent.putExtra("device_name", deviceName);
                intent.putExtra("total_uses", totalUses);
                intent.putExtra("free_uses", freeUses);
                intent.putExtra("daily_uses", dailyUses);
                intent.putExtra("daily_uses_left", dailyUsesLeft);
                intent.putExtra("weekly_uses", weeklyUses);
                intent.putExtra("weekly_uses_left", weeklyUsesLeft);
                intent.putExtra("monthly_uses", monthlyUses);
                intent.putExtra("monthly_uses_left", monthlyUsesLeft);
                startActivity(intent);
            }
        });

//        navigate to humidity chart activity
        humidityChartButton = findViewById(R.id.see_humidity_button);
        humidityChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceDetailsActivity.this, HumidityChartActivity.class);
                intent.putExtra("device_id", deviceId);
                intent.putExtra("device_name", deviceName);
                intent.putExtra("total_uses", totalUses);
                intent.putExtra("free_uses", freeUses);
                intent.putExtra("daily_uses", dailyUses);
                intent.putExtra("daily_uses_left", dailyUsesLeft);
                intent.putExtra("weekly_uses", weeklyUses);
                intent.putExtra("weekly_uses_left", weeklyUsesLeft);
                intent.putExtra("monthly_uses", monthlyUses);
                intent.putExtra("monthly_uses_left", monthlyUsesLeft);
                startActivity(intent);
            }
        });
    }
}