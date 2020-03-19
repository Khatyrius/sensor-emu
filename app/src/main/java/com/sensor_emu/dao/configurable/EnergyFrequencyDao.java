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

    @Query("SELECT * FROM `ENERGY_F`")
    public abstract List<EnergyFrequency> getAll();
}
