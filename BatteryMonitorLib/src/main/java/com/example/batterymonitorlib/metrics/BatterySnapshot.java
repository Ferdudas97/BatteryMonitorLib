package com.example.batterymonitorlib.metrics;

import java.time.LocalDateTime;

public class BatterySnapshot {
    private final Double batteryLevelInPrc;
    private final Long mah;
    private final LocalDateTime time;

    public BatterySnapshot(Double batteryLevelInPrc, Long mah, LocalDateTime time) {
        this.batteryLevelInPrc = batteryLevelInPrc;
        this.mah = mah;
        this.time = time;
    }

    public BatterySnapshot(Double batteryLevelInPrc, Long mah) {
        this.time = LocalDateTime.now();
        this.batteryLevelInPrc = batteryLevelInPrc;
        this.mah = mah;
    }

    public Double getBatteryLevelInPrc() {
        return batteryLevelInPrc;
    }

    public Long getMah() {
        return mah;
    }

    public LocalDateTime getTime() {
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