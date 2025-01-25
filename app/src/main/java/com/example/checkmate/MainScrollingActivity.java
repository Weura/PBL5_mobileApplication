package com.example.checkmate;

import android.os.Bundle;

import com.example.checkmate.data.api.ApiClient;
import com.example.checkmate.data.api.ApiService;
import com.example.checkmate.data.api.modelApi.Device;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkmate.databinding.ActivityMainScrollingBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScrollingActivity extends AppCompatActivity {

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
        deviceAdapter = new DeviceAdapter(this, new ArrayList<>());

        // Ustawienie LayoutManager do RecyclerView
        recyclerViewDevice.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDevice.setAdapter(deviceAdapter);

//        // Kliknięcie na element listy
//        deviceListView.setOnItemClickListener((parent, view, position, id) -> {
//            OnePieceResponse selectedFruit = fruitList.get(position);
//            openDetailsActivity(selectedFruit);
//        });

//        FloatingActionButton fab = binding.fab;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Fetch data from API
        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getDeviceInfo().enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Zaktualizuj dane w adapterze
                    deviceAdapter.updateDevices(response.body());
                } else {
                    Toast.makeText(MainScrollingActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(MainScrollingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}