package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.configurable.HumidityFrequency;
import java.util.List;

@Dao
public abstract class HumidityFrequencyDao extends GenericDao<HumidityFrequency> {

    @Query("SELECT * FROM `HUM_F` WHERE `id`=:id")
    public abstract HumidityFrequency getById(final long id);

    @Query("SELECT * FROM `HUM_F` ORDER BY `id` DESC LIMIT 0, 1")
    public abstract HumidityFrequency getByHighestId();

    @Query("SELECT * FROM `HUM_F`")
    public abstract List<HumidityFrequency> getAll();

    @Query("SELECT * FROM `HUM_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<HumidityFrequency> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `HUM_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `HUM_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd AND `id`<:id")
    public abstract void deleteByTimestampAndId(final long timestampStart, final long timestampEnd, final long id);

    @Override
    @Query("SELECT `ts` FROM `HUM_F` ORDER BY `ts` ASC LIMIT 0, 1")
    public abstract long getFirstTimestamp();

    @Override
    @Query("SELECT `ts` FROM `HUM_F` ORDER BY `ts` DESC LIMIT 0, 1")
    public abstract long getLastTimestamp();
}
