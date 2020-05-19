package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.configurable.PressureFrequency;
import java.util.List;

@Dao
public abstract class PressureFrequencyDao extends GenericDao<PressureFrequency> {

    @Query("SELECT * FROM `PRESS_F` WHERE `id`=:id")
    public abstract PressureFrequency getById(final long id);

    @Query("SELECT * FROM `PRESS_F` ORDER BY `id` DESC LIMIT 0, 1")
    public abstract PressureFrequency getByHighestId();

    @Query("SELECT * FROM `PRESS_F`")
    public abstract List<PressureFrequency> getAll();

    @Query("SELECT * FROM `PRESS_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<PressureFrequency> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `PRESS_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `PRESS_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd AND `id`<:id")
    public abstract void deleteByTimestampAndId(final long timestampStart, final long timestampEnd, final long id);

    @Override
    @Query("SELECT `ts` FROM `PRESS_F` ORDER BY `ts` ASC LIMIT 0, 1")
    public abstract long getFirstTimestamp();

    @Override
    @Query("SELECT `ts` FROM `PRESS_F` ORDER BY `ts` DESC LIMIT 0, 1")
    public abstract long getLastTimestamp();
}
