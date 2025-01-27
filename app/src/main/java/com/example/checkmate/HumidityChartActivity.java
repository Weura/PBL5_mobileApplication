package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.checkmate.data.api.ApiClient;
import com.example.checkmate.data.api.ApiService;
import com.example.checkmate.data.api.modelApi.Humidity;
import com.example.checkmate.data.api.modelApi.Temperature;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HumidityChartActivity extends AppCompatActivity {

    private LineChart lineChart;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_chart);

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

        lineChart = findViewById(R.id.line_chart_humidity);
        setupChart();

        returnButton = findViewById(R.id.return_from_humidity_button);
        returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(HumidityChartActivity.this, DeviceDetailsActivity.class);
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

        fetchHumidity(deviceId);
    }

    private void setupChart() {
        Description description = new Description();
        description.setText("");
        description.setPosition(150f, 15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);

        // x - time
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        // Y - humidity
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);
        yAxis.setDrawGridLines(false);
    }

    private void fetchHumidity(int deviceId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Humidity>> call = apiService.getDeviceHumidity(deviceId);
        call.enqueue(new Callback<List<Humidity>>() {
            @Override
            public void onResponse(Call<List<Humidity>> call, Response<List<Humidity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Humidity> humidities = response.body();
                    if (!humidities.isEmpty()) {
                        updateChartWithHumidityData(humidities);
                    } else {
                        Toast.makeText(HumidityChartActivity.this, "No humidity measurements available.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(HumidityChartActivity.this, "Failed to fetch humidity data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Humidity>> call, Throwable t) {
                Toast.makeText(HumidityChartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateChartWithHumidityData(List<Humidity> humidities) {
        List<Entry> humidityEntries = new ArrayList<>();
        List<String> timeLabels = new ArrayList<>();

//        for displaying highest and lowest value
        final float[] minTimeInMinutes = {Float.MAX_VALUE};
        final float[] maxTimeInMinutes = {Float.MIN_VALUE};
        float minHumidity = Float.MAX_VALUE;
        float maxHumidity = Float.MIN_VALUE;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        try {
            long referenceTime = dateFormat.parse(humidities.get(0).getTimestamp()).getTime();

            for (int i = 0; i < humidities.size(); i++) {
                Humidity temp = humidities.get(i);

                long timeInMillis = dateFormat.parse(temp.getTimestamp()).getTime();
                long timeInMinutes = (timeInMillis - referenceTime) / 60000;

                // Dodaj dane do wykresu
                humidityEntries.add(new Entry(timeInMinutes, (float) temp.getHumidity()));
                timeLabels.add(temp.getTimestamp()); // Możesz dodać formatowanie czasu jako etykiety

                if (temp.getHumidity() < minHumidity) {
                    minHumidity = (float) temp.getHumidity();
                    minTimeInMinutes[0] = timeInMinutes;
                }
                if (temp.getHumidity() > maxHumidity) {
                    maxHumidity = (float) temp.getHumidity();
                    maxTimeInMinutes[0] = timeInMinutes;
                }
            }

            LineDataSet dataSet = new LineDataSet(humidityEntries, "");
            dataSet.setColor(Color.BLUE);
            dataSet.setDrawCircles(false);

            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getPointLabel(Entry entry) {
                    if (entry.getX() == minTimeInMinutes[0] || entry.getX() == maxTimeInMinutes[0]) {
                        return String.valueOf(entry.getY());
                    }
                    return "";
                }
            });

            LineData lineData = new LineData(dataSet);
            lineChart.setData(lineData);
            lineChart.getLegend().setEnabled(false);

            XAxis xAxis = lineChart.getXAxis();
            // number of labels on x axis
            xAxis.setLabelCount(5, true);
            // TODO: if need to be changed rn: 10 min
            xAxis.setGranularity(10f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            // change to format HH:mm
            xAxis.setValueFormatter(new ValueFormatter() {
                private final SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

                @Override
                public String getFormattedValue(float value) {
                    long timeInMillis = referenceTime + ((long) value * 60000);
                    return mFormat.format(new Date(timeInMillis));
                }
            });

            lineChart.invalidate();

        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error in parsing data", Toast.LENGTH_SHORT).show();
        }
    }
}