package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericMeasurementDao;
import com.sensor_emu.model.measurement.Pressure;
import java.util.List;

@Dao
public abstract class PressureDao extends GenericMeasurementDao<Pressure> {

    @Query("SELECT * FROM `PRESS` WHERE `id`=:id")
    public abstract Pressure getById(final long id);

    @Query("SELECT * FROM `PRESS`")
    public abstract List<Pressure> getAll();

    @Query("SELECT * FROM `PRESS` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<Pressure> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `PRESS` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);
}
