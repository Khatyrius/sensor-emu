package com.sensor_emu.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.sensor_emu.dao.delete.DeleteDao;
import com.sensor_emu.dao.get.GetDao;
import com.sensor_emu.dao.list.ListDao;
import com.sensor_emu.dao.set.SetDao;
import com.sensor_emu.model.delete.DeleteResponse;
import com.sensor_emu.model.get.GetResponse;
import com.sensor_emu.model.list.ListResponse;
import com.sensor_emu.model.set.SetResponse;

@Database(entities = {
        ListResponse.class,
        GetResponse.class,
        SetResponse.class,
        DeleteResponse.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ListDao listDao();

    public abstract GetDao getDao();

    public abstract SetDao setDao();

    public abstract DeleteDao deleteDao();
}
