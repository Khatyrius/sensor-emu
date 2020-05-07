package com.sensor_emu.dao.measurement;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.sensor_emu.model.measurement.Energy;
import java.util.List;

@Dao
public abstract class EnergyDao {

    @Query("SELECT * FROM `ENERGY` WHERE `id`=:id")
    public abstract Energy getById(final long id);

    @Query("SELECT * FROM `ENERGY`")
    public abstract List<Energy> getAll();

    @Query("SELECT * FROM `ENERGY` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<Energy> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Insert
    public abstract void insert(final Energy... params);

    @Update
    public abstract void update(final Energy energy);

    @Delete
    public abstract void delete(final Energy energy);
}
