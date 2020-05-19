package com.sensor_emu.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.sensor_emu.model.AbstractEntity;
import java.util.List;

@Dao
public abstract class GenericDao<T extends AbstractEntity> {

    @Insert
    public abstract void insert(final T... params);

    @Update
    public abstract void update(final T param);

    @Delete
    public abstract void delete(final T param);

    @Query("SELECT `ts` FROM `PRESS`")
    public abstract long getFirstTimestamp();

    @Query("SELECT * FROM `PRESS`")
    public abstract long getLastTimestamp();

    @Query("SELECT * FROM `PRESS` WHERE `id`=:id")
    public abstract T getById(final long id);

    @Query("SELECT * FROM `PRESS`")
    public abstract List<T> getAll();

    @Query("SELECT * FROM `PRESS` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract List<T> getByTimestamp(final long timestampStart, final long timestampEnd);

    @Query("DELETE FROM `PRESS` WHERE `ts`>=:timestampStart AND `ts`<=:timestampEnd")
    public abstract void deleteByTimestamp(final long timestampStart, final long timestampEnd);
}
