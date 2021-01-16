package com.example.batterymonitorlib.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MeasureEntity.class}, version = 2)
public abstract class MeasurementDatabase extends RoomDatabase {

    public abstract MeasurementDao measurementDao();
}
