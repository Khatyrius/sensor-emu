package com.sensor_emu.model.delete;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DELETE")
public class DeleteResponse {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "result")
    public String result;

}
