package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.configurable.EnergyFrequency;
import java.util.List;

@Dao
public abstract class EnergyFrequencyDao extends GenericDao<EnergyFrequency> {

    @Query("SELECT * FROM `ENERGY_F` WHERE `id`=:id")
    public abstract EnergyFrequency getById(final long id);

    @Query("SELECT * FROM `ENERGY_F` ORDER BY `id` DESC LIMIT 0, 1")
    public abstract EnergyFrequency getByHighestId();

    @Query("SELECT * FROM `ENERGY_F`")
    public abstract List<EnergyFrequency> getAll();

    @Query("SELECT * FROM `ENERGY_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<EnergyFrequency> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `ENERGY_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `ENERGY_F` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd AND `id`<:id")
    public abstract void deleteByTimestampAndId(final long timestampStart, final long timestampEnd, final long id);

    @Override
    @Query("SELECT `ts` FROM `ENERGY_F` ORDER BY `ts` ASC LIMIT 0, 1")
    public abstract long getFirstTimestamp();

    @Override
    @Query("SELECT `ts` FROM `ENERGY_F` ORDER BY `ts` DESC LIMIT 0, 1")
    public abstract long getLastTimestamp();
}
