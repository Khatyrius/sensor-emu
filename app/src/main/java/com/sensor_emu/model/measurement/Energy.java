package com.sensor_emu.model.measurement;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Objects;

@Entity(tableName = "energy")
public class Energy {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "BAT_V")
    private float batteryVoltage;

    @ColumnInfo(name = "BAT_I")
    private float batteryCurrent;

    @ColumnInfo(name = "SOLAR_V")
    private float solarVoltage;

    @ColumnInfo(name = "SOLAR_I")
    private float solarCurrent;

    @ColumnInfo(name = "NODE_V")
    private float nodeVoltage;

    @ColumnInfo(name = "NODE_U")
    private float nodeCurrent;

    @ColumnInfo(name = "ts")
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Energy{" +
                "id=" + id +
                ", batteryVoltage=" + batteryVoltage +
                ", batteryCurrent=" + batteryCurrent +
                ", solarVoltage=" + solarVoltage +
                ", solarCurrent=" + solarCurrent +
                ", nodeVoltage=" + nodeVoltage +
                ", nodeCurrent=" + nodeCurrent +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Energy energy = (Energy) o;
        return id == energy.id &&
                Float.compare(energy.batteryVoltage, batteryVoltage) == 0 &&
                Float.compare(energy.batteryCurrent, batteryCurrent) == 0 &&
                Float.compare(energy.solarVoltage, solarVoltage) == 0 &&
                Float.compare(energy.solarCurrent, solarCurrent) == 0 &&
                Float.compare(energy.nodeVoltage, nodeVoltage) == 0 &&
                Float.compare(energy.nodeCurrent, nodeCurrent) == 0 &&
                Float.compare(energy.timestamp, timestamp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(id, batteryVoltage, batteryCurrent, solarVoltage, solarCurrent, nodeVoltage,
                        nodeCurrent, timestamp);
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public float getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(final float batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public float getBatteryCurrent() {
        return batteryCurrent;
    }

    public void setBatteryCurrent(final float batteryCurrent) {
        this.batteryCurrent = batteryCurrent;
    }

    public float getSolarVoltage() {
        return solarVoltage;
    }

    public void setSolarVoltage(final float solarVoltage) {
        this.solarVoltage = solarVoltage;
    }

    public float getSolarCurrent() {
        return solarCurrent;
    }

    public void setSolarCurrent(final float solarCurrent) {
        this.solarCurrent = solarCurrent;
    }

    public float getNodeVoltage() {
        return nodeVoltage;
    }

    public void setNodeVoltage(final float nodeVoltage) {
        this.nodeVoltage = nodeVoltage;
    }

    public float getNodeCurrent() {
        return nodeCurrent;
    }

    public void setNodeCurrent(final float nodeCurrent) {
        this.nodeCurrent = nodeCurrent;
    }
}
