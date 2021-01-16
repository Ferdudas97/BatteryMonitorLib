package com.example.batterymonitorlib.metrics;

import android.content.Context;
import android.os.BatteryManager;

import com.example.batterymonitorlib.db.MeasurementRepository;
import com.facebook.battery.metrics.devicebattery.DeviceBatteryMetricsCollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BatteryMetricMonitor {
    private final BatteryManager manager;
    private final DeviceBatteryMetricsCollector sCollector;
    private final Map<MeasuredActionId, Measure> collectedMetrics = new HashMap<>();
    private Context mainContext;
    private final MeasurementRepository repository;

    private Long mahPerBatteryPercent = 0l;

    public BatteryMetricMonitor(Context context, MeasurementRepository repository) {
        this.mainContext = context;
        this.manager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        this.sCollector = new DeviceBatteryMetricsCollector(context);
        this.repository = repository;
        init();
    }

    private void init() {
        final Long currentBatteryInPercents = manager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        final Long currentMah = manager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        mahPerBatteryPercent = currentMah / currentBatteryInPercents;
    }

    public MeasuredActionId startComputing(final MetricType metricType, final String actionName) {
        final Measure metric;
        switch (metricType) {
            case FACEBOOK:
                metric = new FacebookMeasure(actionName, new DeviceBatteryMetricsCollector(mainContext), mahPerBatteryPercent);
                break;
            case RX:
                metric = new RxMeasure(actionName, mainContext, mahPerBatteryPercent);
                break;
            case NATIVE:
            default:
                metric = new NativeMeasure(actionName, manager, mahPerBatteryPercent);
        }
        metric.startMeasurement();
        collectedMetrics.put(metric.id, metric);
        return metric.id;
    }

    public void finishComputing(final MeasuredActionId id) {
        Optional.ofNullable(collectedMetrics.get(id))
                .ifPresent(this::finishComputing);
    }
    private void finishComputing(final Measure measure) {
        measure.endMeasurement();
        repository.save(measure);
    }

    public void compute(final List<MetricType> metricTypes, final String actionName, final Runnable runnable) {
        List<MeasuredActionId> ids = new ArrayList<>();
        for(MetricType metricType : metricTypes) {
            ids.add(startComputing(metricType,actionName));
        }

        runnable.run();

        for(MeasuredActionId id : ids) {
            finishComputing(id);
        }
    }

    public void compute(final MetricType metricType, final String actionName, final Runnable runnable) {
        compute(Collections.singletonList(metricType), actionName, runnable);
    }
}