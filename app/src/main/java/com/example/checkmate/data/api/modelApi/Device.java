package com.example.checkmate.data.api.modelApi;

public class Device {

    private int daily_uses;
    private int free_uses;
    private int id;
    private boolean is_operating;
    private int monthly_uses;
    private String name;
    private int owner_id;
    private String owner_name;
    private int total_uses;
    private int weekly_uses;

    // Getters and Setters

    public int getDailyUses() {
        return daily_uses;
    }

    public void setDailyUses(int daily_uses) {
        this.daily_uses = daily_uses;
    }

    public int getFreeUses() {
        return free_uses;
    }

    public void setFreeUses(int free_uses) {
        this.free_uses = free_uses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOperating() {
        return is_operating;
    }

    public void setOperating(boolean is_operating) {
        this.is_operating = is_operating;
    }

    public int getMonthlyUses() {
        return monthly_uses;
    }

    public void setMonthlyUses(int monthly_uses) {
        this.monthly_uses = monthly_uses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwnerName() {
        return owner_name;
    }

    public void setOwnerName(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getTotalUses() {
        return total_uses;
    }

    public void setTotalUses(int total_uses) {
        this.total_uses = total_uses;
    }

    public int getWeeklyUses() {
        return weekly_uses;
    }

    public void setWeeklyUses(int weekly_uses) {
        this.weekly_uses = weekly_uses;
    }
}
