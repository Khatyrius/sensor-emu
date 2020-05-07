package com.sensor_emu.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.sensor_emu.dao.configurable.EnergyFrequencyDao;
import com.sensor_emu.dao.configurable.HumidityFrequencyDao;
import com.sensor_emu.dao.configurable.PressureFrequencyDao;
import com.sensor_emu.dao.configurable.TemperatureFrequencyDao;
import com.sensor_emu.dao.configurable.TimestampDao;
import com.sensor_emu.dao.measurement.EnergyDao;
import com.sensor_emu.dao.measurement.HumidityDao;
import com.sensor_emu.dao.measurement.PressureDao;
import com.sensor_emu.dao.measurement.TemperatureDao;
import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import com.sensor_emu.model.configurable.Timestamp;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.model.measurement.Humidity;
import com.sensor_emu.model.measurement.Pressure;
import com.sensor_emu.model.measurement.Temperature;

@Database(entities = {
        EnergyFrequency.class,
        HumidityFrequency.class,
        PressureFrequency.class,
        TemperatureFrequency.class,
        Timestamp.class,
        Energy.class,
        Humidity.class,
        Pressure.class,
        Temperature.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "sensor-emu";
    private static volatile AppDatabase appDatabase;

    public static synchronized AppDatabase getInstance(final Context context) {
        if (appDatabase == null) {
            appDatabase = create(context);
        }
        return appDatabase;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    public abstract EnergyFrequencyDao getEnergyFrequencyDao();

    public abstract HumidityFrequencyDao getHumidityFrequencyDao();

    public abstract PressureFrequencyDao getPressureFrequencyDao();

    public abstract TemperatureFrequencyDao getTemperatureFrequencyDao();

    public abstract TimestampDao getTimestampDao();

    public abstract EnergyDao getEnergyDao();

    public abstract HumidityDao getHumidityDao();

    public abstract PressureDao getPressureDao();

    public abstract TemperatureDao getTemperatureDao();
}
