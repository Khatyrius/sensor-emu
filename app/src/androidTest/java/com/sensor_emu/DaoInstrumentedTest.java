package com.sensor_emu;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.sensor_emu.dao.measurement.EnergyDao;
import com.sensor_emu.db.AppDatabase;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.utils.TestUtil;
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
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        energyDao = db.getEnergyDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void shouldWriteEnergyAndReadInList() throws Exception {
        // given
        final Energy energy = TestUtil.createEnergy(
                1L, 123,
                456, 789,
                1011, 1213,
                1415, 1617
        );

        // when
        energyDao.insert(energy);

        // then
        final List<Energy> byTimestamp = energyDao.getAllByTimestamp(energy.getTimestamp());
        assertEquals(byTimestamp.get(0), energy);
    }

}
