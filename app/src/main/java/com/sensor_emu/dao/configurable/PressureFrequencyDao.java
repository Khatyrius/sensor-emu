package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;

import com.sensor_emu.dao.GenericConfigurableDao;
import com.sensor_emu.model.configurable.PressureFrequency;
import java.util.List;

@Dao
public abstract class PressureFrequencyDao extends GenericConfigurableDao<PressureFrequency> {

    @Query("SELECT * FROM `PRESS_F` WHERE `id`=:id")
    public abstract PressureFrequency getById(final long id);

    @Query("SELECT * FROM `PRESS_F` ORDER BY `id` DESC LIMIT 0, 1")
    public abstract PressureFrequency getByHighestId();

    @Query("SELECT * FROM `PRESS_F`")
    public abstract List<PressureFrequency> getAll();

    @Query("SELECT * FROM `PRESS_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<PressureFrequency> getByTimestamp(final long timestampStart, final long timestampEnd);
}
