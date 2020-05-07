package com.sensor_emu.service;

import android.content.Context;

import com.sensor_emu.dao.configurable.EnergyFrequencyDao;
import com.sensor_emu.dao.configurable.HumidityFrequencyDao;
import com.sensor_emu.dao.configurable.PressureFrequencyDao;
import com.sensor_emu.dao.configurable.TemperatureFrequencyDao;
import com.sensor_emu.db.AppDatabase;
import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.model.configurable.TemperatureFrequency;

import java.util.List;

public class ConfigurableService {

    private final AppDatabase db;
    private final EnergyFrequencyDao energyFrequencyDao;
    private final HumidityFrequencyDao humidityFrequencyDao;
    private final PressureFrequencyDao pressureFrequencyDao;
    private final TemperatureFrequencyDao temperatureFrequencyDao;

    public ConfigurableService(final Context context) {
        db = AppDatabase.getInstance(context);
        energyFrequencyDao = db.getEnergyFrequencyDao();
        humidityFrequencyDao = db.getHumidityFrequencyDao();
        pressureFrequencyDao = db.getPressureFrequencyDao();
        temperatureFrequencyDao = db.getTemperatureFrequencyDao();
    }

    public void setEnergyFrequency(final int value) {
        final EnergyFrequency energyFrequency = new EnergyFrequency();
        energyFrequency.setValue(value);
        energyFrequency.setTimestamp(System.currentTimeMillis());
        energyFrequencyDao.insert(energyFrequency);
    }

    public void setHumidityFrequency(final int value) {
        final HumidityFrequency humidityFrequency = new HumidityFrequency();
        humidityFrequency.setValue(value);
        humidityFrequency.setTimestamp(System.currentTimeMillis());
        humidityFrequencyDao.insert(humidityFrequency);
    }

    public void setPressureFrequency(final int value) {
        final PressureFrequency pressureFrequency = new PressureFrequency();
        pressureFrequency.setValue(value);
        pressureFrequency.setTimestamp(System.currentTimeMillis());
        pressureFrequencyDao.insert(pressureFrequency);
    }

    public void setTemperatureFrequency(final int value) {
        final TemperatureFrequency temperatureFrequency = new TemperatureFrequency();
        temperatureFrequency.setValue(value);
        temperatureFrequency.setTimestamp(System.currentTimeMillis());
        temperatureFrequencyDao.insert(temperatureFrequency);
    }

    public List<EnergyFrequency> getEnergyFrequencyList() {
        return energyFrequencyDao.getAll();
    }

    public List<EnergyFrequency> getEnergyFrequencyListByTimestamp(final long timestampStart, final long timestampEnd) {
        return energyFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public List<HumidityFrequency> getHumidityFrequencyList() {
        return humidityFrequencyDao.getAll();
    }

    public List<HumidityFrequency> getHumidityFrequencyListByTimestamp(final long timestampStart, final long timestampEnd) {
        return humidityFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public List<PressureFrequency> getPressureFrequencyList() {
        return pressureFrequencyDao.getAll();
    }

    public List<PressureFrequency> getPressureFrequencyListByTimestamp(final long timestampStart, final long timestampEnd) {
        return pressureFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public List<TemperatureFrequency> getTemperatureFrequencyList() {
        return temperatureFrequencyDao.getAll();
    }

    public List<TemperatureFrequency> getTemperatureFrequencyListByTimestamp(final long timestampStart, final long timestampEnd) {
        return temperatureFrequencyDao.getByTimestamp(timestampStart, timestampStart);
    }
}
