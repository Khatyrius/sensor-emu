package com.sensor_emu.service.thread;

import android.content.Context;

import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.service.ConfigurableService;
import com.sensor_emu.service.MeasurementService;

public class PressureThread implements Runnable {

    private final MeasurementService measurementService;
    private final ConfigurableService configurableService;
    private PressureFrequency frequency;

    public PressureThread(final Context context) {
        measurementService = new MeasurementService(context);
        configurableService = new ConfigurableService(context);
    }

    @Override
    public void run() {
        while(true) {
            measurementService.savePressure();
            frequency = configurableService.getNewestPressureFrequency();
            if(frequency != null) {
                try {
                    Thread.sleep(frequency.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
