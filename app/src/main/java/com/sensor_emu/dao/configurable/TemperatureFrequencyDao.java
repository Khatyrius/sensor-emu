package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;

import com.sensor_emu.dao.GenericConfigurableDao;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import java.util.List;

@Dao
public abstract class TemperatureFrequencyDao extends GenericConfigurableDao<TemperatureFrequency> {

    @Query("SELECT * FROM `TEMP_F` WHERE `id`=:id")
    public abstract TemperatureFrequency getById(final long id);

    @Query("SELECT * FROM `TEMP_F` ORDER BY `id` DESC LIMIT 0, 1")
    public abstract TemperatureFrequency getByHighestId();

    @Query("SELECT * FROM `TEMP_F`")
    public abstract List<TemperatureFrequency> getAll();

    @Query("SELECT * FROM `TEMP_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<TemperatureFrequency> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `TEMP_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `TEMP_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd AND `id`<:id")
    public abstract void deleteByTimestampAndId(final long timestampStart, final long timestampEnd, final long id);
}
