package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TemperatureChartActivity extends AppCompatActivity {

    private LineChart lineChart;
    private List<String> xValues;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_chart);

        Intent newIntent = getIntent();
        int deviceId = newIntent.getIntExtra("device_id", 0);
        String deviceName = newIntent.getStringExtra("device_name");

        int totalUses = newIntent.getIntExtra("total_uses", 0);
        int freeUses = newIntent.getIntExtra("free_uses", 0);

        int dailyUses = newIntent.getIntExtra("daily_uses", 0);
        int dailyUsesLeft = newIntent.getIntExtra("daily_uses_left", 0);
        int weeklyUses = newIntent.getIntExtra("weekly_uses", 0);
        int weeklyUsesLeft = newIntent.getIntExtra("weekly_uses_left", 0);
        int monthlyUses = newIntent.getIntExtra("monthly_uses", 0);
        int monthlyUsesLeft = newIntent.getIntExtra("monthly_uses_left", 0);

        lineChart = findViewById(R.id.line_chart_temperature);

        Description description = new Description();
        description.setText(("Temperature"));
        description.setPosition(150f, 15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels((false));

        xValues = Arrays.asList("Nadun", "Kamal","John");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(3);
        xAxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0, 10f));
        entries1.add(new Entry(1, 10f));
        entries1.add(new Entry(2, 15f));
        entries1.add(new Entry(3, 45f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 5f));
        entries2.add(new Entry(1, 15f));
        entries2.add(new Entry(2, 25f));
        entries2.add(new Entry(3, 30f));

        LineDataSet dataSet1 = new LineDataSet(entries1, "Maths");
        dataSet1.setColor(Color.BLUE);

        LineDataSet dataSet2 = new LineDataSet(entries2, "Science");
        dataSet2.setColor(Color.RED);

        LineData lineData = new LineData(dataSet1, dataSet2);

        lineChart.setData(lineData);

        lineChart.invalidate();

        returnButton = findViewById(R.id.return_from_temperature_button);
        returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(TemperatureChartActivity.this, DeviceDetailsActivity.class);
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
            finish();
        });
    }
}