package com.sensor_emu.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

public class AbstractEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @JsonIgnore
    private long id;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
