package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.measurement.Pressure;
import java.util.List;

@Dao
public abstract class PressureDao extends GenericDao<Pressure> {

    @Query("SELECT * FROM `PRESS` WHERE `id`=:id")
    public abstract Pressure getById(final long id);

    @Query("SELECT * FROM `PRESS`")
    public abstract List<Pressure> getAll();
}
