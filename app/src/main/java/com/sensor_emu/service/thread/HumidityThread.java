package com.sensor_emu.service.thread;

import android.content.Context;

import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.service.ConfigurableService;
import com.sensor_emu.service.MeasurementService;

public class HumidityThread implements Runnable{

    private final MeasurementService measurementService;
    private final ConfigurableService configurableService;
    private HumidityFrequency frequency;
    private static final int DEFAULT_FREQUENCY = 5000;

    public HumidityThread(final Context context) {
        measurementService = new MeasurementService(context);
        configurableService = new ConfigurableService(context);
    }

    @Override
    public void run() {
        while(true) {
            measurementService.saveHumidity();
            frequency = configurableService.getNewestHumidityFrequency();
            if(frequency != null) {
                try {
                    Thread.sleep(frequency.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                configurableService.setHumidityFrequency(DEFAULT_FREQUENCY);
                try {
                    Thread.sleep(DEFAULT_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
