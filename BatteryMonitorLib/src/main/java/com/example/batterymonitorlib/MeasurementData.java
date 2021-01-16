package com.example.batterymonitorlib;


import com.example.batterymonitorlib.metrics.BatterySnapshot;
import com.example.batterymonitorlib.metrics.MeasuredActionId;

public class MeasurementData {
    private BatterySnapshot initialMetrics;
    private BatterySnapshot finalMetrics;
    private String actionName;
    private String metricName;
    private MeasuredActionId id;
    private Double batteryChangeInPrc;
    private Long batteryChangeInMah;

    public MeasurementData(BatterySnapshot initialMetrics, BatterySnapshot finalMetrics, String actionName, String metricName, MeasuredActionId id, Double batteryChangeInPrc, Long batteryChangeInMah) {
        this.initialMetrics = initialMetrics;
        this.finalMetrics = finalMetrics;
        this.actionName = actionName;
        this.metricName = metricName;
        this.id = id;
        this.batteryChangeInPrc = batteryChangeInPrc;
        this.batteryChangeInMah = batteryChangeInMah;
    }

    public BatterySnapshot getInitialMetrics() {
        return initialMetrics;
    }

    public BatterySnapshot getFinalMetrics() {
        return finalMetrics;
    }

    public String getActionName() {
        return actionName;
    }

    public String getMetricName() {
        return metricName;
    }

    public MeasuredActionId getId() {
        return id;
    }

    public Double getBatteryChangeInPrc() {
        return batteryChangeInPrc;
    }

    public Long getBatteryChangeInMah() {
        return batteryChangeInMah;
    }

    @Override
    public String toString() {
        return "MeasurementData{" +
                "initialMetrics=" + initialMetrics +
                ", finalMetrics=" + finalMetrics +
                ", actionName='" + actionName + '\'' +
                ", metricName='" + metricName + '\'' +
                ", id=" + id +
                ", batteryChangeInPrc=" + batteryChangeInPrc +
                ", batteryChangeInMah=" + batteryChangeInMah +
                '}';
    }
}
