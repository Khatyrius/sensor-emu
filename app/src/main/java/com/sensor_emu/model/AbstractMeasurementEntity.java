package com.sensor_emu.model;

import androidx.room.ColumnInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;

@JsonPropertyOrder({
        "ts",
        "value"
})
public class AbstractMeasurementEntity extends AbstractEntity {

    @ColumnInfo(name = "value")
    @JsonProperty("value")
    private float value;

    @ColumnInfo(name = "ts")
    @JsonProperty("ts")
    private long timestamp;

    public float getValue() {
        return value;
    }

    public void setValue(final float value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final AbstractMeasurementEntity that = (AbstractMeasurementEntity) o;
        return Float.compare(that.value, value) == 0 &&
                timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, timestamp);
    }

    @Override
    public String toString() {
        return "AbstractMeasurementEntity{" +
                "value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
