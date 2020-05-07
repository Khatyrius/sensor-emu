package com.sensor_emu.dao.configurable;

import androidx.room.Dao;
import androidx.room.Query;

import com.sensor_emu.dao.GenericConfigurableDao;
import com.sensor_emu.model.configurable.Timestamp;
import java.util.List;

@Dao
public abstract class TimestampDao extends GenericConfigurableDao<Timestamp> {

    @Query("SELECT * FROM `TIMESTAMP` WHERE `id`=:id")
    public abstract Timestamp getById(final long id);

    @Query("SELECT * FROM `TIMESTAMP`")
    public abstract List<Timestamp> getAll();
}
