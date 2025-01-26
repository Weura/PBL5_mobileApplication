package com.example.checkmate.data.api.modelApi;

public class Voltage {
        private int id;
        private int device_id;
        private double voltage;
        private String timestamp;

        // Constructor
        public Voltage(int id, int device_id, double voltage, String timestamp) {
            this.id = id;
            this.device_id = device_id;
            this.voltage = voltage;
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

        public double getVoltage() {
            return voltage;
        }

        public void setVoltage(double voltage) {
            this.voltage = voltage;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
}
