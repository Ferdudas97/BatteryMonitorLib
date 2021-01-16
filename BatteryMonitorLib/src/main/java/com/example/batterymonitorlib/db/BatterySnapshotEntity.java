package com.example.batterymonitorlib.db;

public class BatterySnapshotEntity {
    private final Double batteryLevelInPrc;
    private final Long mah;
    private final String time;

    public BatterySnapshotEntity(String time, Double batteryLevelInPrc, Long mah) {
        this.time = time;
        this.batteryLevelInPrc = batteryLevelInPrc;
        this.mah = mah;
    }

    public Double getBatteryLevelInPrc() {
        return batteryLevelInPrc;
    }

    public Long getMah() {
        return mah;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "BatterySnapshot{" +
                "time=" + time +
                ", batteryLevelInPrc=" + batteryLevelInPrc +
                ", mah=" + mah +
                "}";
    }
}