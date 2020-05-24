package com.sensor_emu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewAnimator;
import androidx.fragment.app.FragmentTransaction;
import com.sensor_emu.bluetooth.bluetoothfragment.BluetoothFragment;
import com.sensor_emu.bluetooth.common.activities.SampleActivityBase;
import com.sensor_emu.bluetooth.common.logger.Log;
import com.sensor_emu.bluetooth.common.logger.LogFragment;
import com.sensor_emu.bluetooth.common.logger.LogWrapper;
import com.sensor_emu.bluetooth.common.logger.MessageOnlyLogFilter;
import com.sensor_emu.thread.EnergyThread;
import com.sensor_emu.thread.HumidityThread;
import com.sensor_emu.thread.PressureThread;
import com.sensor_emu.thread.TemperatureThread;

public class MainActivity extends SampleActivityBase {

    public static final String TAG = "MainActivity";

    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            final BluetoothFragment fragment = new BluetoothFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        new Thread(new TemperatureThread(getApplicationContext())).start();
        new Thread(new HumidityThread(getApplicationContext())).start();
        new Thread(new PressureThread(getApplicationContext())).start();
        new Thread(new EnergyThread(getApplicationContext())).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toggle_log:
                mLogShown = !mLogShown;
                ViewAnimator output = findViewById(R.id.sample_output);
                if (mLogShown) {
                    output.setDisplayedChild(1);
                } else {
                    output.setDisplayedChild(0);
                }
                supportInvalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initializeLogging() {
        final LogWrapper logWrapper = new LogWrapper();

        Log.setLogNode(logWrapper);

        final MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        final LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i(TAG, "Ready");
    }
}
