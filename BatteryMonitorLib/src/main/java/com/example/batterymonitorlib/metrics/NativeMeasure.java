package com.example.batterymonitorlib.metrics;

import android.os.BatteryManager;

public class NativeMeasure extends Measure{
    private final BatteryManager manager;

    public NativeMeasure(String actionName, BatteryManager manager, Long mahPerPercentChange) {
        super(mahPerPercentChange);
        this.actionName = actionName;
        this.manager = manager;
        this.metricName = "Native";
    }

    @Override
    public void startMeasurement() {
        Long batteryLevel = manager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        Long mah = manager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        initialMetrics = new BatterySnapshot(batteryLevel.doubleValue(), mah);
    }

    @Override
    public void endMeasurement() {
        Long batteryLevel = manager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        Long mah = manager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        finalMetrics = new BatterySnapshot(batteryLevel.doubleValue(), mah);
        calcChange();
    }

    @Override
    public void calcChange() {
        batteryChangeInMah = (initialMetrics.getMah() - finalMetrics.getMah());
        batteryChangeInPrc = (initialMetrics.getMah() - finalMetrics.getMah()) / (double)mahPerPercentChange;
    }
}
