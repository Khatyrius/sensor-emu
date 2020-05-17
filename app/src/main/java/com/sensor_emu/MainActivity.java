package com.sensor_emu;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.sensor_emu.thread.EnergyThread;
import com.sensor_emu.thread.HumidityThread;
import com.sensor_emu.thread.PressureThread;
import com.sensor_emu.thread.TemperatureThread;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new TemperatureThread(getApplicationContext())).start();
        new Thread(new HumidityThread(getApplicationContext())).start();
        new Thread(new PressureThread(getApplicationContext())).start();
        new Thread(new EnergyThread(getApplicationContext())).start();
    }

    //TODO delete method and test button
    public void testMethod(final View view) {

    }

    //TODO delete method and test button
    public void testMethod1(final View view) {

    }
}
