package com.sensor_emu.model.list;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LIST")
public class ListResponse {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "variable")
    public String variable;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "values")
    public Integer values;

    @ColumnInfo(name = "ts_first")
    public Integer tsFirst;

    @ColumnInfo(name = "ts_last")
    public Integer tsLast;
}
