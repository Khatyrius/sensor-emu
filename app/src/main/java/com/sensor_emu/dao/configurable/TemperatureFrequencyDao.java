package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import java.util.List;

@Dao
public abstract class TemperatureFrequencyDao extends GenericDao<TemperatureFrequency> {

    @Query("SELECT * FROM `TEMP_F` WHERE `id`=:id")
    public abstract TemperatureFrequency getById(final long id);

    @Query("SELECT * FROM `TEMP_F`")
    public abstract List<TemperatureFrequency> getAll();
}
