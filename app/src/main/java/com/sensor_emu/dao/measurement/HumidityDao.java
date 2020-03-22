package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Query;
import com.sensor_emu.dao.GenericDao;
import com.sensor_emu.model.measurement.Humidity;
import java.util.List;

@Dao
public abstract class HumidityDao extends GenericDao<Humidity> {

    @Query("SELECT * FROM `HUM` WHERE `id`=:id")
    public abstract Humidity getById(final long id);

    @Query("SELECT * FROM `HUM`")
    public abstract List<Humidity> getAll();
}
