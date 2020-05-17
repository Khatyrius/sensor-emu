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

    public EnergyFrequency getNewestEnergyFrequency() {
        return energyFrequencyDao.getByHighestId();
    }

    public HumidityFrequency getNewestHumidityFrequency() {
        return humidityFrequencyDao.getByHighestId();
    }

    public PressureFrequency getNewestPressureFrequency() {
        return pressureFrequencyDao.getByHighestId();
    }

    public TemperatureFrequency getNewestTemperatureFrequency() {
        return temperatureFrequencyDao.getByHighestId();
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
        return temperatureFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
    }

    public void deleteEnergyFrequencyByTimestamp(final long timestampStart, final long timestampEnd) {
        final List<EnergyFrequency> list = energyFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
        final EnergyFrequency energyFrequency = energyFrequencyDao.getByHighestId();
        if(list != null) {
            if(list.contains(energyFrequency)) {
                energyFrequencyDao.deleteByTimestampAndId(timestampStart, timestampEnd, energyFrequency.getId());
            } else {
                energyFrequencyDao.deleteByTimestamp(timestampStart, timestampEnd);
            }
        }
    }

    public void deleteHumidityFrequencyByTimestamp(final long timestampStart, final long timestampEnd) {
        final List<HumidityFrequency> list = humidityFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
        final HumidityFrequency humidityFrequency = humidityFrequencyDao.getByHighestId();
        if(list != null) {
            if(list.contains(humidityFrequency)) {
                humidityFrequencyDao.deleteByTimestampAndId(timestampStart, timestampEnd, humidityFrequency.getId());
            } else {
                humidityFrequencyDao.deleteByTimestamp(timestampStart, timestampEnd);
            }
        }
    }

    public void deletePressureFrequencyByTimestamp(final long timestampStart, final long timestampEnd) {
        final List<PressureFrequency> list = pressureFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
        final PressureFrequency pressureFrequency = pressureFrequencyDao.getByHighestId();
        if(list != null) {
            if(list.contains(pressureFrequency)) {
                pressureFrequencyDao.deleteByTimestampAndId(timestampStart, timestampEnd, pressureFrequency.getId());
            } else {
                pressureFrequencyDao.deleteByTimestamp(timestampStart, timestampEnd);
            }
        }
    }

    public void deleteTemperatureFrequencyByTimestamp(final long timestampStart, final long timestampEnd) {
        final List<TemperatureFrequency> list = temperatureFrequencyDao.getByTimestamp(timestampStart, timestampEnd);
        final TemperatureFrequency temperatureFrequency = temperatureFrequencyDao.getByHighestId();
        if(list != null) {
            if (list.contains(temperatureFrequency)) {
                temperatureFrequencyDao.deleteByTimestampAndId(timestampStart, timestampEnd, temperatureFrequency.getId());
            } else {
                temperatureFrequencyDao.deleteByTimestamp(timestampStart, timestampEnd);
            }
        }
    }
}
