package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericMeasurementDao;
import com.sensor_emu.model.measurement.Temperature;
import java.util.List;

@Dao
public abstract class TemperatureDao extends GenericMeasurementDao<Temperature> {

    @Query("SELECT * FROM `TEMP` WHERE `id`=:id")
    public abstract Temperature getById(final long id);

    @Query("SELECT * FROM `TEMP`")
    public abstract List<Temperature> getAll();

    @Query("SELECT * FROM `TEMP` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<Temperature> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `TEMP` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);
}
