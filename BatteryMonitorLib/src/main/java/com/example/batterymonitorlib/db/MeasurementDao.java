package com.example.batterymonitorlib.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeasurementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurement(MeasureEntity measureEntitie);
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Single<String> insertMeasurement(MeasureEntity measureEntities);
    @Query("Select * from measurement")
    List<MeasureEntity> getMeasurements();
}
