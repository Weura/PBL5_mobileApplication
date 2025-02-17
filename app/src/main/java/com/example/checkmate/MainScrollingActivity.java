package com.example.checkmate;

import android.content.Intent;
import android.os.Bundle;

import com.example.checkmate.data.OnDeviceClickListener;
import com.example.checkmate.data.api.ApiClient;
import com.example.checkmate.data.api.ApiService;
import com.example.checkmate.data.api.modelApi.Device;
import com.example.checkmate.data.api.modelApi.UserDevice;
import com.example.checkmate.data.model.LoggedInUser;
import com.example.checkmate.data.model.UserSessionManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import com.example.checkmate.databinding.ActivityMainScrollingBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScrollingActivity extends AppCompatActivity implements OnDeviceClickListener {

    private ActivityMainScrollingBinding binding;
    private RecyclerView recyclerViewDevice;
    private DeviceAdapter deviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Pobieranie referencji do ListView z layoutu
        recyclerViewDevice = findViewById(R.id.recyclerViewDevice);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        // Inicjalizacja adaptera bez danych
        deviceAdapter = new DeviceAdapter(this, new ArrayList<>(), this);

        // Ustawienie LayoutManager do RecyclerView
        recyclerViewDevice.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDevice.setAdapter(deviceAdapter);

        UserSessionManager userSessionManager = UserSessionManager.getInstance(MainScrollingActivity.this);
        LoggedInUser currentUser = userSessionManager.getLoggedInUser();

        if (currentUser != null) {
            fetchUserDevice(currentUser.getUserId()); // Wywołaj API tylko, jeśli userId jest poprawny
        } else {
            Toast.makeText(MainScrollingActivity.this, "User is not logged in", Toast.LENGTH_SHORT).show();
        }

        // Fetch data from API
        fetchUserDevice(currentUser.getUserId());
    }

    private void fetchUserDevice(int userId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<UserDevice> call = apiService.getUserDevice(userId);
        call.enqueue(new Callback<UserDevice>() {
            @Override
            public void onResponse(Call<UserDevice> call, Response<UserDevice> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Zaktualizuj dane w adapterze
                    List<Device> devices = response.body().getDevices();
                    deviceAdapter.updateDevices(devices);
                } else {
                    Toast.makeText(MainScrollingActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserDevice> call, Throwable t) {
                Toast.makeText(MainScrollingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeviceClick(Device device) {
        // Start DeviceDetailsActivity and pass the device ID or other details
        Intent intent = new Intent(this, DeviceDetailsActivity.class);
        intent.putExtra("device_id", device.getId());
        intent.putExtra("device_name", device.getName());
        intent.putExtra("total_uses", device.getTotalUses());
        intent.putExtra("free_uses", device.getFreeUses());
        intent.putExtra("daily_uses", device.getDailyUses());
        intent.putExtra("daily_uses_left", device.getDailyUsesLeft());
        intent.putExtra("weekly_uses", device.getWeeklyUses());
        intent.putExtra("weekly_uses_left", device.getWeeklyUsesLeft());
        intent.putExtra("monthly_uses", device.getMonthlyUses());
        intent.putExtra("monthly_uses_left", device.getMonthlyUsesLeft());
        startActivity(intent);
    }
}