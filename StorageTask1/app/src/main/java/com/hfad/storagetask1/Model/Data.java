package com.hfad.storagetask1.Model;

import android.arch.persistence.room.Entity;

@Entity(tableName = "Data")
public class Data {
    private String value;

    public Data(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
