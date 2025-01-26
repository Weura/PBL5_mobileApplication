package com.example.checkmate.data.api.modelApi;

public class Temperature {

    private int id;
    private int device_id;
    private double temperature;
    private String timestamp;

    // Constructor
    public Temperature(int id, int device_id, double temperature, String timestamp) {
        this.id = id;
        this.device_id = device_id;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return device_id;
    }

    public void setDeviceId(int device_id) {
        this.device_id = device_id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

