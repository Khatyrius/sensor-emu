package com.sensor_emu;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sensor_emu.dao.configurable.EnergyFrequencyDao;
import com.sensor_emu.dao.configurable.HumidityFrequencyDao;
import com.sensor_emu.dao.configurable.PressureFrequencyDao;
import com.sensor_emu.dao.configurable.TemperatureFrequencyDao;
import com.sensor_emu.dao.configurable.TimestampDao;
import com.sensor_emu.dao.measurement.EnergyDao;
import com.sensor_emu.dao.measurement.HumidityDao;
import com.sensor_emu.dao.measurement.PressureDao;
import com.sensor_emu.dao.measurement.TemperatureDao;
import com.sensor_emu.db.AppDatabase;
import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import com.sensor_emu.model.configurable.Timestamp;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.model.measurement.Humidity;
import com.sensor_emu.model.measurement.Pressure;
import com.sensor_emu.model.measurement.Temperature;
import com.sensor_emu.utils.TestUtil;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DaoInstrumentedTest {

    private EnergyDao energyDao;
    private HumidityDao humidityDao;
    private PressureDao pressureDao;
    private TemperatureDao temperatureDao;
    private EnergyFrequencyDao energyFrequencyDao;
    private HumidityFrequencyDao humidityFrequencyDao;
    private PressureFrequencyDao pressureFrequencyDao;
    private TemperatureFrequencyDao temperatureFrequencyDao;
    private TimestampDao timestampDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        energyDao = db.getEnergyDao();
        humidityDao = db.getHumidityDao();
        pressureDao = db.getPressureDao();
        temperatureDao = db.getTemperatureDao();
        energyFrequencyDao = db.getEnergyFrequencyDao();
        humidityFrequencyDao = db.getHumidityFrequencyDao();
        pressureFrequencyDao = db.getPressureFrequencyDao();
        temperatureFrequencyDao = db.getTemperatureFrequencyDao();
        timestampDao = db.getTimestampDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void shouldInsertEnergyAndGetAllByTimestamp() {
        // given
        final Energy energy1 = TestUtil.createEnergy(
                1L, 123,
                456, 789,
                1011, 1213,
                1415, 1617
        );

        final Energy energy2 = TestUtil.createEnergy(
                2L, 456,
                789, 910,
                213, 134,
                867, 343
        );

        final Energy energy3 = TestUtil.createEnergy(
                3L, 645,
                456, 767889,
                1061, 980,
                532, 52
        );

        // when
        energyDao.insert(energy1);
        energyDao.insert(energy2);
        energyDao.insert(energy3);

        // then
        final List<Energy> result = energyDao.getByTimestamp(100L, 343L);
        assertEquals(result.get(0), energy2);
    }

    @Test
    public void shouldInsertHumidityAndGetAll() {
        // given
        final Humidity humidity1 = TestUtil.createHumidity(1L, 123, 456);
        final Humidity humidity2 = TestUtil.createHumidity(2L, 789, 1000);
        final List<Humidity> humidityList = Arrays.asList(humidity1, humidity2);

        // when
        humidityDao.insert(humidity1);
        humidityDao.insert(humidity2);

        // then
        final List<Humidity> result = humidityDao.getAll();
        assertEquals(result, humidityList);
    }

    @Test
    public void shouldInsertPressureAndGetById() {
        // given
        final Long id = 2L;
        final Pressure pressure1 = TestUtil.createPressure(1L, 123, 456);
        final Pressure pressure2 = TestUtil.createPressure(id, 111, 1000);

        // when
        pressureDao.insert(pressure1);
        pressureDao.insert(pressure2);

        // then
        final Pressure result = pressureDao.getById(id);
        assertEquals(result, pressure2);
    }

    @Test
    public void shouldInsertTemperatureAndGetById() {
        // given
        final Long id = 1L;
        final Temperature temperature1 = TestUtil.createTemperature(id, 123, 456);
        final Temperature temperature2 = TestUtil.createTemperature(2L, 111, 1000);

        // when
        temperatureDao.insert(temperature1);
        temperatureDao.insert(temperature2);

        // then
        final Temperature result = temperatureDao.getById(id);
        assertEquals(result, temperature1);
    }

    @Test
    public void shouldInsertEnergyFrequencyAndGetById() {
        // given
        final Long id = 1L;
        final EnergyFrequency energyFrequency1 = TestUtil.createEnergyFrequency(id, 123, 456);
        final EnergyFrequency energyFrequency2 = TestUtil.createEnergyFrequency(2L, 111, 1000);

        // when
        energyFrequencyDao.insert(energyFrequency1);
        energyFrequencyDao.insert(energyFrequency2);

        // then
        final EnergyFrequency result = energyFrequencyDao.getById(id);
        assertEquals(result, energyFrequency1);
    }

    @Test
    public void shouldInsertHumidityFrequencyAndGetAll() {
        // given
        final HumidityFrequency humidityFrequency1 = TestUtil.createHumidityFrequency(1L, 123, 456);
        final HumidityFrequency humidityFrequency2 = TestUtil.createHumidityFrequency(2L, 789, 1000);
        final List<HumidityFrequency> humidityFrequencyList = Arrays.asList(humidityFrequency1, humidityFrequency2);

        // when
        humidityFrequencyDao.insert(humidityFrequency1);
        humidityFrequencyDao.insert(humidityFrequency2);

        // then
        final List<HumidityFrequency> result = humidityFrequencyDao.getAll();
        assertEquals(result, humidityFrequencyList);
    }

    @Test
    public void shouldInsertAndUpdatePressureFrequencyAndGetById() {
        // given
        final long id = 2L;
        final PressureFrequency pressureFrequency1 = TestUtil.createPressureFrequency(1L, 123, 456);
        final PressureFrequency pressureFrequency2 = TestUtil.createPressureFrequency(id, 789, 1000);
        final PressureFrequency pressureFrequency3 = TestUtil.createPressureFrequency(id, 111, 3333);

        // when
        pressureFrequencyDao.insert(pressureFrequency1);
        pressureFrequencyDao.insert(pressureFrequency2);
        pressureFrequencyDao.update(pressureFrequency3);

        // then
        final PressureFrequency result = pressureFrequencyDao.getById(id);
        assertEquals(result, pressureFrequency3);
    }

    @Test
    public void shouldInsertAndDeleteTemperatureFrequency() {
        // given
        final TemperatureFrequency temperatureFrequency1 = TestUtil.createTemperatureFrequency(1L, 123, 456);
        final TemperatureFrequency temperatureFrequency2 = TestUtil.createTemperatureFrequency(2L, 789, 1000);
        final TemperatureFrequency temperatureFrequency3 = TestUtil.createTemperatureFrequency(3L, 333, 2222);

        final List<TemperatureFrequency> temperatureFrequencyList = Arrays.asList(temperatureFrequency1, temperatureFrequency3);

        // when
        temperatureFrequencyDao.insert(temperatureFrequency1);
        temperatureFrequencyDao.insert(temperatureFrequency2);
        temperatureFrequencyDao.insert(temperatureFrequency3);
        temperatureFrequencyDao.delete(temperatureFrequency2);

        // then
        final List<TemperatureFrequency> result = temperatureFrequencyDao.getAll();
        assertEquals(result, temperatureFrequencyList);
    }

    @Test
    public void shouldInsertTimestampAndGetById() {
        // given
        final Long id = 1L;
        final Timestamp timestamp = TestUtil.createTimestamp(id, 123, 456);

        // when
        timestampDao.insert(timestamp);

        // then
        final Timestamp result = timestampDao.getById(id);
        assertEquals(result, timestamp);
    }
}
