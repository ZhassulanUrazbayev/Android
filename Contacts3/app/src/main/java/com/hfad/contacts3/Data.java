package com.hfad.contacts3;

import android.net.Uri;

public class Data {

    String name;
    String number;

    public Data(String name, String number) {
        this.name = name;
        this.number = number;
    }



    public void setNumber(String number) {

        this.number = number;

    }

    public String getName() {

        return name;

    }

    public String getNumber() {

        return number;

    }

    public void setName(String name) {

        this.name = name;

    }


}