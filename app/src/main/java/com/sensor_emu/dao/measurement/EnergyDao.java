package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.measurement.Energy;
import java.util.List;

@Dao
public abstract class EnergyDao extends GenericDao<Energy> {

    @Query("SELECT * FROM `ENERGY` WHERE `id`=:id")
    public abstract Energy getById(final long id);

    @Query("SELECT * FROM `ENERGY`")
    public abstract List<Energy> getAll();

    @Query("SELECT * FROM `ENERGY` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<Energy> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `ENERGY` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);

    @Override
    @Query("SELECT `ts` FROM `ENERGY` ORDER BY `ts` ASC LIMIT 0, 1")
    public abstract long getFirstTimestamp();

    @Override
    @Query("SELECT `ts` FROM `ENERGY` ORDER BY `ts` DESC LIMIT 0, 1")
    public abstract long getLastTimestamp();
}
