package com.sensor_emu.bluetooth.common.logger;

public interface LogNode {

    void println(int priority, String tag, String msg, Throwable tr);

}
