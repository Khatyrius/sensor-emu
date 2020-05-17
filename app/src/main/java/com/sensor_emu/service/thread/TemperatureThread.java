package com.sensor_emu.service.thread;

import android.content.Context;

import com.sensor_emu.model.configurable.TemperatureFrequency;
import com.sensor_emu.service.ConfigurableService;
import com.sensor_emu.service.MeasurementService;

public class TemperatureThread implements Runnable{

    private final MeasurementService measurementService;
    private final ConfigurableService configurableService;
    private TemperatureFrequency frequency;
    private static final int DEFAULT_FREQUENCY = 5000;

    public TemperatureThread(final Context context) {
        measurementService = new MeasurementService(context);
        configurableService = new ConfigurableService(context);
    }

    @Override
    public void run() {
        while(true) {
            measurementService.saveTemperature();
            frequency = configurableService.getNewestTemperatureFrequency();
            if(frequency != null) {
                try {
                    Thread.sleep(frequency.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                configurableService.setTemperatureFrequency(DEFAULT_FREQUENCY);
                try {
                    Thread.sleep(DEFAULT_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
