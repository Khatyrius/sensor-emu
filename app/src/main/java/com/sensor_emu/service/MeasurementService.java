package com.sensor_emu.service;

import android.content.Context;
import com.sensor_emu.dao.measurement.EnergyDao;
import com.sensor_emu.dao.measurement.HumidityDao;
import com.sensor_emu.dao.measurement.PressureDao;
import com.sensor_emu.dao.measurement.TemperatureDao;
import com.sensor_emu.db.AppDatabase;
import com.sensor_emu.model.AbstractEntity;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.model.measurement.Humidity;
import com.sensor_emu.model.measurement.Pressure;
import com.sensor_emu.model.measurement.Temperature;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MeasurementService {

    private final AppDatabase db;
    private final TemperatureDao temperatureDao;
    private final HumidityDao humidityDao;
    private final PressureDao pressureDao;
    private final EnergyDao energyDao;

    public MeasurementService(final Context context) {
        db = AppDatabase.getInstance(context);
        temperatureDao = db.getTemperatureDao();
        humidityDao = db.getHumidityDao();
        pressureDao = db.getPressureDao();
        energyDao = db.getEnergyDao();
    }

    public void saveTemperature() {
        final Temperature temperature = generateTemperature();
        temperature.setTimestamp(System.currentTimeMillis());
        temperatureDao.insert(temperature);
    }

    public void saveHumidity() {
        final Humidity humidity = generateHumidity();
        humidity.setTimestamp(System.currentTimeMillis());
        humidityDao.insert(humidity);
    }

    public void savePressure() {
        final Pressure pressure = generatePressure();
        pressure.setTimestamp(System.currentTimeMillis());
        pressureDao.insert(pressure);
    }

    public void saveEnergy() {
        final Energy energy = generateEnergy();
        energy.setTimestamp(System.currentTimeMillis());
        energyDao.insert(energy);
    }

    public List<Temperature> getTemperatureList() {
        return temperatureDao.getAll();
    }

    public List<Temperature> getTemperatureListByTimestamp(final long timestampStart,
            final long timestampEnd) {
        return temperatureDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public long getTemperatureFirstTimestamp() {
        return temperatureDao.getFirstTimestamp();
    }

    public long getTemperatureLastTimestamp() {
        return temperatureDao.getLastTimestamp();
    }

    public List<Humidity> getHumidityList() {
        return humidityDao.getAll();
    }

    public List<Humidity> getHumidityListByTimestamp(final long timestampStart,
            final long timestampEnd) {
        return humidityDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public long getHumidityFirstTimestamp() {
        return humidityDao.getFirstTimestamp();
    }

    public long getHumidityLastTimestamp() {
        return humidityDao.getLastTimestamp();
    }

    public List<Pressure> getPressureList() {
        return pressureDao.getAll();
    }

    public List<Pressure> getPressureListByTimestamp(final long timestampStart,
            final long timestampEnd) {
        return pressureDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public long getPressureFirstTimestamp() {
        return pressureDao.getFirstTimestamp();
    }

    public long getPressureLastTimestamp() {
        return pressureDao.getLastTimestamp();
    }

    public List<Energy> getEnergyList() {
        return energyDao.getAll();
    }

    public List<Energy> getEnergyListByTimestamp(final long timestampStart,
            final long timestampEnd) {
        return energyDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public long getEnergyFirstTimestamp() {
        return energyDao.getFirstTimestamp();
    }

    public long getEnergyLastTimestamp() {
        return energyDao.getLastTimestamp();
    }

    public void deleteTemperatureByTimestamp(final long timestampStart, final long timestampEnd) {
        temperatureDao.deleteByTimestamp(timestampStart, timestampEnd);
    }

    public void deleteHumidityByTimestamp(final long timestampStart, final long timestampEnd) {
        humidityDao.deleteByTimestamp(timestampStart, timestampEnd);
    }

    public void deletePressureByTimestamp(final long timestampStart, final long timestampEnd) {
        pressureDao.deleteByTimestamp(timestampStart, timestampEnd);
    }

    public void deleteEnergyByTimestamp(final long timestampStart, final long timestampEnd) {
        energyDao.deleteByTimestamp(timestampStart, timestampEnd);
    }

    private Temperature generateTemperature() {
        final Temperature temperature = new Temperature();
        final float value = (new Random().nextFloat()) * 50 - 20;
        temperature.setValue(value);
        return temperature;
    }

    private Humidity generateHumidity() {
        final Humidity humidity = new Humidity();
        final float value = (new Random().nextFloat()) * 101;
        humidity.setValue(value);
        return humidity;
    }

    private Pressure generatePressure() {
        final Pressure pressure = new Pressure();
        final float value = (new Random().nextFloat()) * 101 + 950;
        pressure.setValue(value);
        return pressure;
    }

    private Energy generateEnergy() {
        final Energy energy = new Energy();
        energy.setBatteryCurrent((new Random().nextFloat()) * 100 + 100);
        energy.setBatteryVoltage((new Random().nextFloat()) + 3);
        energy.setSolarCurrent((new Random().nextFloat()) * 100 + 200);
        energy.setSolarVoltage((new Random().nextFloat()) + 6);
        energy.setNodeCurrent((new Random().nextFloat()) * 100 + 300);
        energy.setNodeVoltage((new Random().nextFloat()) + 9);
        return energy;
    }

    public List<Class<? extends AbstractEntity>> getClassList() {
        return Arrays.asList(Energy.class, Humidity.class, Pressure.class, Temperature.class);
    }
}
