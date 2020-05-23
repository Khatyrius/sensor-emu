package com.sensor_emu.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sensor_emu.R;
import com.sensor_emu.adapter.DeviceListAdapter;
import java.util.ArrayList;
import java.util.List;

public class BluetoothService {

    private BluetoothAdapter bluetoothAdapter;
    private List<BluetoothDevice> devices;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                final BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                devices.add(device);
            }
        }
    };
    private DeviceListAdapter deviceListAdapter;

    public BluetoothService(final Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devices = new ArrayList<>();
        deviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_view, devices);
    }

    public BroadcastReceiver getReceiver() {
        return receiver;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public void setBluetoothAdapter(final BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public List<BluetoothDevice> getDevices() {
        return devices;
    }

    public void setDevices(final List<BluetoothDevice> devices) {
        this.devices = devices;
    }

    public DeviceListAdapter getDeviceListAdapter() {
        return deviceListAdapter;
    }

    public void setDeviceListAdapter(final DeviceListAdapter deviceListAdapter) {
        this.deviceListAdapter = deviceListAdapter;
    }
}
