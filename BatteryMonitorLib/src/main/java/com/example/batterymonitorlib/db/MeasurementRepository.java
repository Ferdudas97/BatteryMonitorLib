package com.example.batterymonitorlib.db;


import com.example.batterymonitorlib.MeasurementData;
import com.example.batterymonitorlib.metrics.BatterySnapshot;
import com.example.batterymonitorlib.metrics.Measure;
import com.example.batterymonitorlib.metrics.MeasuredActionId;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MeasurementRepository {

    private final MeasurementDao dao;

    public MeasurementRepository(MeasurementDao dao) {
        this.dao = dao;
    }


    public void save(Measure measure) {
        Completable.fromRunnable(() -> dao.insertMeasurement(mapToEntity(measure))).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public List<MeasurementData> getAll() {
        return dao.getMeasurements().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());

    }

    private MeasurementData mapToDomain(MeasureEntity entity) {
        return new MeasurementData(mapToDomain(entity.initialState),
                mapToDomain(entity.endState),
                entity.actionName,
                entity.metricName,
                new MeasuredActionId(UUID.fromString(entity.id)),
                entity.batteryChangeInPrc,
                entity.batteryChangeInMah);
    }

    private BatterySnapshot mapToDomain(BatterySnapshotEntity entity) {
        return new BatterySnapshot(entity.getBatteryLevelInPrc(), entity.getMah(), LocalDateTimeConverter.toDate(entity.getTime()));
    }

    private MeasureEntity mapToEntity(Measure measure) {
        return new MeasureEntity(measure.getId().getId().toString(),
                measure.getActionName(),
                measure.getMetricName(),
                measure.getBatteryChangeInPrc(),
                measure.getBatteryChangeInMah(),
                mapToEntity(measure.getInitialMetrics()),
                mapToEntity(measure.getFinalMetrics()));
    }

    private BatterySnapshotEntity mapToEntity(BatterySnapshot snapshot) {
        return new BatterySnapshotEntity(LocalDateTimeConverter.toDateString(snapshot.getTime()),
                snapshot.getBatteryLevelInPrc(),
                snapshot.getMah());
    }
}
