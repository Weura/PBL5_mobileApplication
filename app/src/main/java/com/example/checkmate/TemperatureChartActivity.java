package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.checkmate.data.api.ApiClient;
import com.example.checkmate.data.api.ApiService;
import com.example.checkmate.data.api.modelApi.Temperature;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemperatureChartActivity extends AppCompatActivity {

    private LineChart lineChart;
    private Button returnButton;
    private Map<String, Double> temperatureData; // Mapa do przechowywania timestamp i temperatury

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_chart);

        // Odbieranie danych o urządzeniu z Intent
        Intent newIntent = getIntent();
        int deviceId = newIntent.getIntExtra("device_id", 0);
        String deviceName = newIntent.getStringExtra("device_name");

        // Inicjalizacja wykresu
        lineChart = findViewById(R.id.line_chart_temperature);
        setupChart();

        // Przycisk powrotu
        returnButton = findViewById(R.id.return_from_temperature_button);
        returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(TemperatureChartActivity.this, DeviceDetailsActivity.class);
            intent.putExtra("device_id", deviceId);
            intent.putExtra("device_name", deviceName);
            startActivity(intent);
            finish();
        });

        // Pobieranie temperatury z API
        fetchTemperature(deviceId);
    }

    // Ustawienia wykresu
    private void setupChart() {
        Description description = new Description();
        description.setText("Temperature over time");
        description.setPosition(150f, 15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);

        // Ustawienia osi X (czas)
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(45f); // Rotacja, jeśli nazwy są długie
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false); // Ukrywa linie siatki

        // Ustawienia osi Y (temperatura)
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);
    }

    // Pobieranie danych z API
    private void fetchTemperature(int deviceId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Temperature>> call = apiService.getDeviceTemperature(deviceId);
        call.enqueue(new Callback<List<Temperature>>() {
            @Override
            public void onResponse(Call<List<Temperature>> call, Response<List<Temperature>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Temperature> temperatures = response.body();
                    if (!temperatures.isEmpty()) {
                        updateChartWithTemperatureData(temperatures);
                    } else {
                        Toast.makeText(TemperatureChartActivity.this, "No temperature measurements available.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TemperatureChartActivity.this, "Failed to fetch temperature data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Temperature>> call, Throwable t) {
                Toast.makeText(TemperatureChartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Aktualizacja wykresu na podstawie pobranych danych
    private void updateChartWithTemperatureData(List<Temperature> temperatures) {
        List<Entry> temperatureEntries = new ArrayList<>();
        List<String> xValues = new ArrayList<>(); // Lista dla osi X (timestamp)

        // Dodanie danych do list
        for (int i = 0; i < temperatures.size(); i++) {
            Temperature temp = temperatures.get(i);
            temperatureEntries.add(new Entry(i, (float) temp.getTemperature()));
            xValues.add(temp.getTimestamp()); // Dodanie timestampu do osi X
        }

        // Ustawienie danych wykresu
        LineDataSet dataSet = new LineDataSet(temperatureEntries, "Temperature");
        dataSet.setColor(Color.BLUE);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        // Ustawienie formatera osi X
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(xValues.size());

        lineChart.invalidate(); // Odświeżenie wykresu
    }
}
