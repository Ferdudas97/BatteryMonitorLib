package com.example.batterymonitorlib.metrics;

import com.facebook.battery.metrics.devicebattery.DeviceBatteryMetrics;
import com.facebook.battery.metrics.devicebattery.DeviceBatteryMetricsCollector;

public class FacebookMeasure extends Measure{
    private final DeviceBatteryMetricsCollector sCollector;
    private final DeviceBatteryMetrics startBatteryLevel;
    private final DeviceBatteryMetrics endBatteryLevel;

    public FacebookMeasure(String actionName, DeviceBatteryMetricsCollector sCollector, Long mahPerPercentChange) {
        super(mahPerPercentChange);
        this.actionName = actionName;
        this.sCollector = sCollector;
        this.startBatteryLevel = sCollector.createMetrics();
        this.endBatteryLevel = sCollector.createMetrics();
        this.metricName = "Facebook";
    }

    @Override
    public void startMeasurement() {
        sCollector.getSnapshot(startBatteryLevel);
        Float batteryLevel = startBatteryLevel.batteryLevelPct;
        initialMetrics = new BatterySnapshot(batteryLevel.doubleValue(), Float.valueOf(startBatteryLevel.batteryLevelPct * mahPerPercentChange).longValue());
    }

    @Override
    public void endMeasurement() {
        sCollector.getSnapshot(endBatteryLevel);
        Float batteryLevel = endBatteryLevel.batteryLevelPct;
        finalMetrics = new BatterySnapshot(batteryLevel.doubleValue(),  Float.valueOf(endBatteryLevel.batteryLevelPct * mahPerPercentChange).longValue());
        calcChange();
    }

    @Override
    public void calcChange() {
        batteryChangeInMah = (initialMetrics.getMah() - finalMetrics.getMah());
        batteryChangeInPrc = (initialMetrics.getMah() - finalMetrics.getMah()) / (double)mahPerPercentChange;
    }
}
