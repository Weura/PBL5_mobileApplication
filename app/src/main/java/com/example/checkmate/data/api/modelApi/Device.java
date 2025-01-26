package com.example.checkmate.data.api.modelApi;

public class Device {
    private int id;
    private boolean is_operating;
    private String name;
    private int owner_id;

    private int total_uses;
    private int monthly_uses;
    private int monthly_uses_left;
    private int weekly_uses;
    private int weekly_uses_left;
    private int daily_uses;
    private int daily_uses_left;
    private int free_uses;
    // Getters and Setters
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


//    ----------------------------------------------------------------

    public int getTotalUses() {
        return total_uses;
    }

    public void setTotalUses(int total_uses) {
        this.total_uses = total_uses;
    }

    public int getMonthlyUses() {
        return monthly_uses;
    }

    public void setMonthlyUses(int monthly_uses) {
        this.monthly_uses = monthly_uses;
    }

    public int getMonthlyUsesLeft() {
        return monthly_uses_left;
    }

    public void setMonthlyUsesLeft(int monthly_uses_left) {
        this.monthly_uses_left = monthly_uses_left;
    }

    public int getWeeklyUses() {
        return weekly_uses;
    }

    public void setWeeklyUses(int weekly_uses) {
        this.weekly_uses = weekly_uses;
    }

    public int getWeeklyUsesLeft() {
        return weekly_uses_left;
    }

    public void setWeeklyUsesLeft(int weekly_uses_left) {
        this.weekly_uses_left = weekly_uses_left;
    }

    public int getDailyUses() {
        return daily_uses;
    }
    public void setDailyUses(int daily_uses) {
        this.daily_uses = daily_uses;
    }
    public int getDailyUsesLeft() {
        return daily_uses_left;
    }
    public void setDailyUsesLeft(int daily_uses_left) {
        this.daily_uses_left = daily_uses_left;
    }


    public int getFreeUses() {
        return free_uses;
    }

    public void setFreeUses(int free_uses) {
        this.free_uses = free_uses;
    }




}
