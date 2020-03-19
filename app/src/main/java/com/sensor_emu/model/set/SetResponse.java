package com.sensor_emu.model.set;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SET")
public class SetResponse {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "result")
    public String result;
}
