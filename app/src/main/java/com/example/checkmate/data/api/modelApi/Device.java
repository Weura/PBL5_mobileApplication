package com.example.checkmate.data.api.modelApi;

public class Device {

    private int dailyUses;
    private int freeUses;
    private int id;
    private boolean isOperating;
    private int monthlyUses;
    private String name;
    private int ownerId;
    private String ownerName;
    private int totalUses;
    private int weeklyUses;

    // Getters and Setters

    public int getDailyUses() {
        return dailyUses;
    }

    public void setDailyUses(int dailyUses) {
        this.dailyUses = dailyUses;
    }

    public int getFreeUses() {
        return freeUses;
    }

    public void setFreeUses(int freeUses) {
        this.freeUses = freeUses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOperating() {
        return isOperating;
    }

    public void setOperating(boolean isOperating) {
        this.isOperating = isOperating;
    }

    public int getMonthlyUses() {
        return monthlyUses;
    }

    public void setMonthlyUses(int monthlyUses) {
        this.monthlyUses = monthlyUses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getTotalUses() {
        return totalUses;
    }

    public void setTotalUses(int totalUses) {
        this.totalUses = totalUses;
    }

    public int getWeeklyUses() {
        return weeklyUses;
    }

    public void setWeeklyUses(int weeklyUses) {
        this.weeklyUses = weeklyUses;
    }
}
