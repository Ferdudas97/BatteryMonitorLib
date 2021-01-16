package com.example.batterymonitorlib.metrics;

import android.content.Context;

import com.github.pwittchen.rxbattery.library.RxBattery;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxMeasure extends Measure {
    private final Context mainContext;
    private Disposable rxBatteryDisposable = null;
    private boolean initial = true;
    private boolean finishing = false;

    public RxMeasure(String actionName, Context context, Long mahPerPercentChange) {
        super(mahPerPercentChange);
        this.actionName = actionName;
        this.mainContext = context;
        this.metricName = "RxBattery";
    }

    @Override
    public void startMeasurement() {
        rxBatteryDisposable = RxBattery
                .observe(this.mainContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(batteryState -> {
                        this.initialMetrics = new BatterySnapshot((double) batteryState.getLevel(), 0l);
                });

    }

    @Override
    public void endMeasurement() {
        RxBattery
                .observe(this.mainContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(batteryState -> {
                    this.finalMetrics = new BatterySnapshot((double) batteryState.getLevel(), 0l);
                });    }

    @Override
    public void calcChange() {
        batteryChangeInMah = (initialMetrics.getMah() - finalMetrics.getMah());
        batteryChangeInPrc = (initialMetrics.getMah() - finalMetrics.getMah()) / (double)mahPerPercentChange;
    }
}
