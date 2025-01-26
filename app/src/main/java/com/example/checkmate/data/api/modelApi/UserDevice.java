package com.example.checkmate.data.api.modelApi;

import java.util.List;

public class UserDevice {

    private List<Device> devices;
    private String user;

    // Constructor
    public UserDevice(List<Device> devices, String user) {
        this.devices = devices;
        this.user = user;
    }

    // Getters and Setters
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

