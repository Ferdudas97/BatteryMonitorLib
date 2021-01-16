package com.example.batterymonitorlib.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "measurement")
public class MeasureEntity {

    @PrimaryKey
    @NonNull
    protected String id;
    @ColumnInfo(name = "action_name")
    protected String actionName;
    @ColumnInfo(name = "metric_name")
    protected String metricName;
    @ColumnInfo(name = "battery_change_in_percents")
    protected Double batteryChangeInPrc = 0.0;
    @ColumnInfo(name = "battery_change_in_mah")
    protected Long batteryChangeInMah = 0l;
    @Embedded(prefix = "initial_")
    protected BatterySnapshotEntity initialState;
    @Embedded(prefix = "end_")
    protected BatterySnapshotEntity endState;

    public MeasureEntity(String id, String actionName, String metricName, Double batteryChangeInPrc, Long batteryChangeInMah, BatterySnapshotEntity initialState, BatterySnapshotEntity endState) {
        this.id = id;
        this.actionName = actionName;
        this.metricName = metricName;
        this.batteryChangeInPrc = batteryChangeInPrc;
        this.batteryChangeInMah = batteryChangeInMah;
        this.initialState = initialState;
        this.endState = endState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasureEntity that = (MeasureEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
