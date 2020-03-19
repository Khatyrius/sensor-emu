package com.sensor_emu.model.get;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GET")
public class GetResponse {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "timestamp")
    public Integer timestamp;

    @ColumnInfo(name = "value")
    public Double value;
}
