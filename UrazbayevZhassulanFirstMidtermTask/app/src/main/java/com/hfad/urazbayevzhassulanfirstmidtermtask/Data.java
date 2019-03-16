package com.hfad.urazbayevzhassulanfirstmidtermtask;

import android.text.Editable;

import java.util.ArrayList;

public class Data {
    String description;
    String hour;
    String minute;
    public Data(String description, String hour, String minute) {
        this.description = description;
        this.hour = hour;
        this.minute = minute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
