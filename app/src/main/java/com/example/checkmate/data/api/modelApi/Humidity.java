package com.example.checkmate.data.api.modelApi;

public class Humidity {

    private int id;
    private int device_id;
    private double humidity;
    private String timestamp;

    // Constructor
    public Humidity(int id, int device_id, double humidity, String timestamp) {
        this.id = id;
        this.device_id = device_id;
        this.humidity = humidity;
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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
