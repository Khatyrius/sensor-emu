package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericMeasurementDao;
import com.sensor_emu.model.measurement.Humidity;
import java.util.List;

@Dao
public abstract class HumidityDao extends GenericMeasurementDao<Humidity> {

    @Query("SELECT * FROM `HUM` WHERE `id`=:id")
    public abstract Humidity getById(final long id);

    @Query("SELECT * FROM `HUM`")
    public abstract List<Humidity> getAll();

    @Query("SELECT * FROM `HUM` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<Humidity> getByTimestamp(final long timestampStart, final long timestampEnd);
}
