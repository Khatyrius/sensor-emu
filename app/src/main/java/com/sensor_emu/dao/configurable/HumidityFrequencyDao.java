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

    @Query("SELECT * FROM `HUM_F`")
    public abstract List<HumidityFrequency> getAll();
}
