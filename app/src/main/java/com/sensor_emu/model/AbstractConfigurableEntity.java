package com.sensor_emu.model;

import androidx.room.ColumnInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;

@JsonPropertyOrder({
        "ts",
        "value"
})
public class AbstractConfigurableEntity extends AbstractEntity {

    @ColumnInfo(name = "value")
    @JsonProperty("value")
    private int value;

    @ColumnInfo(name = "ts")
    @JsonProperty("ts")
    private long timestamp;

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
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
        final AbstractConfigurableEntity that = (AbstractConfigurableEntity) o;
        return value == that.value &&
                timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, timestamp);
    }

    @Override
    public String toString() {
        return "AbstractConfigurableEntity{" +
                "value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
