package com.example.batterymonitorlib.metrics;

public abstract class Measure {
    protected BatterySnapshot initialMetrics;
    protected BatterySnapshot finalMetrics;
    protected String actionName;
    protected String metricName;
    protected MeasuredActionId id = new MeasuredActionId();
    protected Double batteryChangeInPrc = 0.0;
    protected Long batteryChangeInMah = 0l;
    protected final Long mahPerPercentChange;

    protected Measure(Long mahPerPercentChange) {
        this.mahPerPercentChange = mahPerPercentChange;
    }

    public abstract void startMeasurement();
    public abstract void endMeasurement();
    public abstract void calcChange();

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
        return "Measure (" + metricName + "){" +
                "initialMetrics=" + initialMetrics +
                "finalMetrics=" + finalMetrics +
                "batteryChangeInPrc=" + batteryChangeInPrc +
                "batteryChangeInMah=" + batteryChangeInMah +
                "}";
    }
}
