package com.sensor_emu;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.sensor_emu.adapter.DeviceListAdapter;
import com.sensor_emu.service.BluetoothService;
import com.sensor_emu.thread.EnergyThread;
import com.sensor_emu.thread.HumidityThread;
import com.sensor_emu.thread.PressureThread;
import com.sensor_emu.thread.TemperatureThread;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BluetoothAdapter bluetoothAdapter;
    private ListView deviceListView;
    private BluetoothService bluetoothService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothService = new BluetoothService(this);
        bluetoothAdapter = bluetoothService.getBluetoothAdapter();

        deviceListView = findViewById(R.id.DEVICE_LIST_VIEW);
        deviceListView.setOnItemClickListener(this);
        deviceListView.setAdapter(bluetoothService.getDeviceListAdapter());

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "BLUETOOTH NOT SUPPORTED", Toast.LENGTH_SHORT).show();
            this.finishAffinity();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.enable();
            }
            final IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(bluetoothService.getReceiver(), filter);

            final Intent discoverableIntent =
                    new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);

            bluetoothService.getDevices().addAll(bluetoothAdapter.getBondedDevices());

            this.run();
        }
    }

    private void run() {
        new Thread(new TemperatureThread(getApplicationContext())).start();
        new Thread(new HumidityThread(getApplicationContext())).start();
        new Thread(new PressureThread(getApplicationContext())).start();
        new Thread(new EnergyThread(getApplicationContext())).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothService.getReceiver());
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view,
            final int i, final long l) {
        bluetoothAdapter.cancelDiscovery();

        final List<BluetoothDevice> devices = bluetoothService.getDevices();

        Log.d(TAG, "onItemClick: You Clicked on a device.");
        String deviceName = devices.get(i).getName();
        String deviceAddress = devices.get(i).getAddress();

        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);
    }

    public void refresh(final View view) {
        bluetoothAdapter.startDiscovery();
        bluetoothService.setDevices(new ArrayList<BluetoothDevice>());
        bluetoothService.getDevices().addAll(bluetoothAdapter.getBondedDevices());
        bluetoothService.setDeviceListAdapter(new DeviceListAdapter(
                this,
                R.layout.device_adapter_view,
                bluetoothService.getDevices())
        );
        deviceListView.setAdapter(bluetoothService.getDeviceListAdapter());
    }
}
