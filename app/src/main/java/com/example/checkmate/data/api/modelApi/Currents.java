package com.example.checkmate.data.api.modelApi;

public class Currents {

    private int id;
    private int device_id;
    private double current;
    private String timestamp;

    // Constructor
    public Currents(int id, int device_id, double current, String timestamp) {
        this.id = id;
        this.device_id = device_id;
        this.current = current;
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

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

