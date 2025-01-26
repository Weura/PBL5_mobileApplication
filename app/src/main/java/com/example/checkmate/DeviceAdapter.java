package com.example.checkmate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkmate.data.api.modelApi.Device;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private Context context;
    private List<Device> devices;

    public DeviceAdapter(Context context, List<Device> devices) {
        this.context = context;
        this.devices = devices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Device device = devices.get(position);
        Log.d("DEVICELogamiks", position + device.getName() + "Uses: " + device.getTotalUses() + "Status: " + device.isOperating());
        holder.deviceName.setText(device.getName());
        holder.deviceTotalUses.setText("Uses: " + device.getTotalUses());
        holder.deviceStatus.setText("Status: " + (device.isOperating() ? "Operating" : "Not Operating"));
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public void updateDevices(List<Device> newDevices) {
        devices.clear();
        devices.addAll(newDevices);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceName;
        public TextView deviceTotalUses;
        public TextView deviceStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.device_name);
            deviceTotalUses = itemView.findViewById(R.id.device_total_uses);
            deviceStatus = itemView.findViewById(R.id.device_status);
        }
    }
}
