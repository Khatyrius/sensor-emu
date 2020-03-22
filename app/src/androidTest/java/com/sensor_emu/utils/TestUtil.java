package com.sensor_emu.utils;

import com.sensor_emu.model.measurement.Energy;

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

}
