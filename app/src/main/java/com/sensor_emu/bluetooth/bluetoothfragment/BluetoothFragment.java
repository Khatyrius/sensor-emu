package com.sensor_emu.bluetooth.bluetoothfragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor_emu.R;
import com.sensor_emu.bluetooth.common.logger.Log;
import com.sensor_emu.model.AbstractEntity;
import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.model.measurement.Humidity;
import com.sensor_emu.model.measurement.Pressure;
import com.sensor_emu.model.measurement.Temperature;
import com.sensor_emu.response.ResponseStatus;
import com.sensor_emu.response.delete.DeleteResponse;
import com.sensor_emu.response.get.GetResponse;
import com.sensor_emu.response.list.ListResponse;
import com.sensor_emu.response.set.SetResponse;
import com.sensor_emu.service.BluetoothService;
import com.sensor_emu.service.ConfigurableService;
import com.sensor_emu.service.MeasurementService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BluetoothFragment extends Fragment {

    private static final String TAG = "BluetoothFragment";

    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    private ObjectMapper objectMapper;

    private String mConnectedDeviceName = null;
    private StringBuffer mOutStringBuffer;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothService bluetoothService = null;

    private ConfigurableService configurableService;
    private MeasurementService measurementService;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            final FragmentActivity activity = getActivity();
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
                            setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            setStatus(R.string.title_connecting);
                            break;
                        case BluetoothService.STATE_LISTEN:
                        case BluetoothService.STATE_NONE:
                            setStatus(R.string.title_not_connected);
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String readMessage = new String(readBuf, 0, msg.arg1);

                    Map<String, Object> json;
                    String command;

                    try {
                        json = objectMapper.readValue(readMessage, Map.class);
                        command = (String) json.get("command");
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        break;
                    }

                    switch (command) {
                        case "set":
                            final Map<String, Object> set_params = (Map<String, Object>) json
                                    .get("set_params");
                            final String variable_set = (String) set_params.get("variable");
                            final int value = Integer.parseInt(set_params.get("value").toString()); // XD
                            SetResponse setResponse = null;

                            switch (variable_set) {
                                case "TEMP_F":
                                    configurableService.setTemperatureFrequency(value);
                                    setResponse = new SetResponse(ResponseStatus.OK);
                                    break;
                                case "HUM_F":
                                    configurableService.setHumidityFrequency(value);
                                    setResponse = new SetResponse(ResponseStatus.OK);
                                    break;
                                case "PRESS_F":
                                    configurableService.setPressureFrequency(value);
                                    setResponse = new SetResponse(ResponseStatus.OK);
                                    break;
                                case "ENERGY_F":
                                    configurableService.setEnergyFrequency(value);
                                    setResponse = new SetResponse(ResponseStatus.OK);
                                    break;
                                default:
                                    break;
                            }
                            try {
                                BluetoothFragment.this
                                        .sendMessage(
                                                objectMapper.writeValueAsString(setResponse));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "get":
                            final Map<String, Object> get_params = (Map<String, Object>) json
                                    .get("get_params");
                            final String var = (String) get_params.get("variable");
                            final long ts_start_get = Long.parseLong(get_params.get("ts_start").toString()); // XD
                            final long ts_stop_get = Long.parseLong(get_params.get("ts_stop").toString()); // XD
                            GetResponse response = null;
                            switch (var) {
                                case "TEMP":
                                    final List<Temperature> temperatureList = measurementService
                                            .getTemperatureListByTimestamp(ts_start_get,
                                                    ts_stop_get);

                                    final List<AbstractEntity> temperatureAbstracts =
                                            new ArrayList<AbstractEntity>(temperatureList);
                                    response = new GetResponse(temperatureAbstracts);
                                    break;
                                case "HUM":
                                    final List<Humidity> humidityList = measurementService
                                            .getHumidityListByTimestamp(ts_start_get, ts_stop_get);

                                    final List<AbstractEntity> abstractHumidities = new ArrayList<AbstractEntity>(
                                            humidityList);

                                    response = new GetResponse(abstractHumidities);
                                    break;
                                case "PRESS":
                                    final List<Pressure> pressureList = measurementService
                                            .getPressureListByTimestamp(ts_start_get, ts_stop_get);

                                    final List<AbstractEntity> abstractPressures = new ArrayList<AbstractEntity>(
                                            pressureList);

                                    response = new GetResponse(abstractPressures);
                                    break;
                                case "ENERGY":
                                    final List<Energy> energyList = measurementService
                                            .getEnergyListByTimestamp(ts_start_get, ts_stop_get);

                                    final List<AbstractEntity> abstractEnergy = new ArrayList<AbstractEntity>(
                                            energyList);

                                    response = new GetResponse(abstractEnergy);
                                    break;
                                case "TEMP_F":
                                    final List<TemperatureFrequency> temperatureFrequencyList = configurableService
                                            .getTemperatureFrequencyListByTimestamp(ts_start_get,
                                                    ts_stop_get);

                                    final List<AbstractEntity> abstractTemperatureFreq = new ArrayList<AbstractEntity>(
                                            temperatureFrequencyList);

                                    response = new GetResponse(abstractTemperatureFreq);
                                    break;
                                case "HUM_F":
                                    final List<HumidityFrequency> humidityFrequencyList = configurableService
                                            .getHumidityFrequencyListByTimestamp(ts_start_get,
                                                    ts_stop_get);

                                    final List<AbstractEntity> abstractHumidityFreq = new ArrayList<AbstractEntity>(
                                            humidityFrequencyList);

                                    response = new GetResponse(abstractHumidityFreq);
                                    break;
                                case "PRESS_F":
                                    final List<PressureFrequency> pressureFrequencyLists = configurableService
                                            .getPressureFrequencyListByTimestamp(ts_start_get,
                                                    ts_stop_get);

                                    final List<AbstractEntity> abstractPressureFreq = new ArrayList<AbstractEntity>(
                                            pressureFrequencyLists);

                                    response = new GetResponse(abstractPressureFreq);
                                    break;
                                case "ENERGY_F":
                                    final List<EnergyFrequency> energyFrequencyList = configurableService
                                            .getEnergyFrequencyListByTimestamp(ts_start_get,
                                                    ts_stop_get);

                                    final List<AbstractEntity> abstractEnergyFreq = new ArrayList<AbstractEntity>(
                                            energyFrequencyList);

                                    response = new GetResponse(abstractEnergyFreq);
                                    break;
                                default:
                                    break;
                            }
                            try {
                                BluetoothFragment.this
                                        .sendMessage(objectMapper.writeValueAsString(response));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "list":
                            try {
                                final ListResponse listResponse = new ListResponse(getContext());
                                BluetoothFragment.this
                                        .sendMessage(objectMapper.writeValueAsString(listResponse));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "delete":
                            final Map<String, Object> del_params = (Map<String, Object>) json
                                    .get("del_params");
                            final String var_delete = (String) del_params.get("variable");
                            final long ts_start_delete = Long.parseLong(del_params.get("ts_start").toString()); // XD
                            final long ts_stop_delete = Long.parseLong(del_params.get("ts_stop").toString()); // XD
                            DeleteResponse deleteResponse = null;
                            switch (var_delete) {
                                case "TEMP":
                                    measurementService.deleteTemperatureByTimestamp(ts_start_delete,
                                            ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "HUM":
                                    measurementService.deleteHumidityByTimestamp(ts_start_delete,
                                            ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "PRESS":
                                    measurementService.deletePressureByTimestamp(ts_start_delete,
                                            ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "ENERGY":
                                    measurementService.deleteEnergyByTimestamp(ts_start_delete,
                                            ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "TEMP_F":
                                    configurableService
                                            .deleteTemperatureFrequencyByTimestamp(ts_start_delete,
                                                    ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "HUM_F":
                                    configurableService
                                            .deleteHumidityFrequencyByTimestamp(ts_start_delete,
                                                    ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "PRESS_F":
                                    configurableService
                                            .deletePressureFrequencyByTimestamp(ts_start_delete,
                                                    ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                case "ENERGY_F":
                                    configurableService
                                            .deleteEnergyFrequencyByTimestamp(ts_start_delete,
                                                    ts_stop_delete);
                                    deleteResponse = new DeleteResponse(ResponseStatus.OK);
                                    break;
                                default:
                                    break;
                            }
                            try {
                                BluetoothFragment.this
                                        .sendMessage(
                                                objectMapper.writeValueAsString(deleteResponse));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }

                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != activity) {
                        Toast.makeText(activity, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        objectMapper = new ObjectMapper();
        configurableService = new ConfigurableService(getContext());
        measurementService = new MeasurementService(getContext());

        if (mBluetoothAdapter == null) {
            final FragmentActivity activity = getActivity();
            Toast.makeText(activity, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            activity.finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mBluetoothAdapter.isEnabled()) {
            final Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else if (bluetoothService == null) {
            setupChat();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bluetoothService != null) {
            bluetoothService.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (bluetoothService != null) {
            if (bluetoothService.getState() == BluetoothService.STATE_NONE) {
                bluetoothService.start();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bluetooth, container, false);
    }

    private void setupChat() {
        Log.d(TAG, "setupChat()");

        bluetoothService = new BluetoothService(getActivity(), mHandler);

        mOutStringBuffer = new StringBuffer();
    }

    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            final Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    private void sendMessage(String message) {
        if (bluetoothService.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(getActivity(), R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }

        if (message.length() > 0) {
            byte[] send = message.getBytes();
            bluetoothService.write(send);

            mOutStringBuffer.setLength(0);
        }
    }

    private void setStatus(int resId) {
        final FragmentActivity activity = getActivity();
        if (null == activity) {
            return;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(resId);
    }

    private void setStatus(CharSequence subTitle) {
        final FragmentActivity activity = getActivity();
        if (null == activity) {
            return;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(subTitle);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    setupChat();
                } else {
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(getActivity(), R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
        }
    }

    private void connectDevice(final Intent data, final boolean secure) {
        final String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        bluetoothService.connect(device, secure);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.bluetooth_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.secure_connect_scan: {
                final Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                return true;
            }
            case R.id.insecure_connect_scan: {
                final Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
                return true;
            }
            case R.id.discoverable: {
                ensureDiscoverable();
                return true;
            }
        }
        return false;
    }
}
