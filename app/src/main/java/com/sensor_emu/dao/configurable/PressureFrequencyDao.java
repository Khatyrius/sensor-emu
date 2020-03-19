package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import java.util.List;

@Dao
public abstract class PressureFrequencyDao extends GenericDao<PressureFrequency> {

    @Query("SELECT * FROM `PRESS_F` WHERE `id`=:id")
    public abstract PressureFrequency getById(final long id);

    @Query("SELECT * FROM `PRESS_F`")
    public abstract List<PressureFrequency> getAll();
}
