package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.measurement.Temperature;
import java.util.List;

@Dao
public abstract class TemperatureDao extends GenericDao<Temperature> {

    @Query("SELECT * FROM `TEMP` WHERE `id`=:id")
    public abstract Temperature getById(final long id);

    @Query("SELECT * FROM `TEMP`")
    public abstract List<Temperature> getAll();
}
