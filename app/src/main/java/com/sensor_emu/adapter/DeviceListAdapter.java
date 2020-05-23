package com.sensor_emu.adapter;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.sensor_emu.R;
import java.util.List;


public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater layoutInflater;
    private List<BluetoothDevice> bluetoothDevices;
    private int viewResourceId;

    public DeviceListAdapter(final Context context, final int resourceId,
            final List<BluetoothDevice> devices) {
        super(context, resourceId, devices);
        this.bluetoothDevices = devices;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resourceId;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    public View getView(final int position, View view, @NonNull final ViewGroup parent) {
        view = layoutInflater.inflate(viewResourceId, null);

        BluetoothDevice device = bluetoothDevices.get(position);

        if (device != null) {
            TextView deviceName = view.findViewById(R.id.tvDeviceName);
            TextView deviceAddress = view.findViewById(R.id.tvDeviceAddress);

            if (deviceName != null) {
                deviceName.setText(device.getName());
            }
            if (deviceAddress != null) {
                deviceAddress.setText(device.getAddress());
            }
        }

        return view;
    }

}