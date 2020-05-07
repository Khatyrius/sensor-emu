package com.sensor_emu.utils;

import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import com.sensor_emu.model.configurable.Timestamp;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.model.measurement.Humidity;
import com.sensor_emu.model.measurement.Pressure;
import com.sensor_emu.model.measurement.Temperature;

public class TestUtil {

    public static Energy createEnergy(final Long id, final float batteryVoltage,
            final float batteryCurrent, final float solarVoltage, final float solarCurrent,
            final float nodeVoltage, final float nodeCurrent, final long timestamp) {
        final Energy energy = new Energy();

        if (id != null) {
            energy.setId(id);
        }

        energy.setBatteryVoltage(batteryVoltage);
        energy.setBatteryCurrent(batteryCurrent);
        energy.setSolarVoltage(solarVoltage);
        energy.setSolarCurrent(solarCurrent);
        energy.setNodeVoltage(nodeVoltage);
        energy.setNodeCurrent(nodeCurrent);
        energy.setTimestamp(timestamp);
        return energy;
    }

    public static Humidity createHumidity(final Long id, final float value, final long timestamp) {
        final Humidity humidity = new Humidity();

        if(id != null ) {
            humidity.setId(id);
        }

        humidity.setValue(value);
        humidity.setTimestamp(timestamp);
        return humidity;
    }

    public static Pressure createPressure(final Long id, final float value, final long timestamp) {
        final Pressure pressure = new Pressure();

        if(id != null ) {
            pressure.setId(id);
        }

        pressure.setValue(value);
        pressure.setTimestamp(timestamp);
        return pressure;
    }

    public static Temperature createTemperature(final Long id, final float value, final long timestamp) {
        final Temperature temperature = new Temperature();

        if(id != null ) {
            temperature.setId(id);
        }

        temperature.setValue(value);
        temperature.setTimestamp(timestamp);
        return temperature;
    }

    public static EnergyFrequency createEnergyFrequency(final Long id, final int value, final long timestamp) {
        final EnergyFrequency energyFrequency = new EnergyFrequency();

        if(id != null ) {
            energyFrequency.setId(id);
        }

        energyFrequency.setValue(value);
        energyFrequency.setTimestamp(timestamp);
        return energyFrequency;
    }

    public static HumidityFrequency createHumidityFrequency(final Long id, final int value, final long timestamp) {
        final HumidityFrequency humidityFrequency = new HumidityFrequency();

        if(id != null ) {
            humidityFrequency.setId(id);
        }

        humidityFrequency.setValue(value);
        humidityFrequency.setTimestamp(timestamp);
        return humidityFrequency;
    }

    public static PressureFrequency createPressureFrequency(final Long id, final int value, final long timestamp) {
        final PressureFrequency pressureFrequency = new PressureFrequency();

        if(id != null ) {
            pressureFrequency.setId(id);
        }

        pressureFrequency.setValue(value);
        pressureFrequency.setTimestamp(timestamp);
        return pressureFrequency;
    }

    public static TemperatureFrequency createTemperatureFrequency(final Long id, final int value, final long timestamp) {
        final TemperatureFrequency temperatureFrequency = new TemperatureFrequency();

        if(id != null ) {
            temperatureFrequency.setId(id);
        }

        temperatureFrequency.setValue(value);
        temperatureFrequency.setTimestamp(timestamp);
        return temperatureFrequency;
    }

    public static Timestamp createTimestamp(final Long id, final int value, final long timestamp) {
        final Timestamp ts = new Timestamp();

        if(id != null ) {
            ts.setId(id);
        }

        ts.setValue(value);
        ts.setTimestamp(timestamp);
        return ts;
    }
}
