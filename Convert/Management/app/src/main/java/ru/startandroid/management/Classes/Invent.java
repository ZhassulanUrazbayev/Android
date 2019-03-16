package ru.startandroid.management.Classes;

public class Invent {
    int id;
    String serial_number;
    String fullname;

    public Invent(int id, String serial_number, String fullname) {
        this.id = id;
        this.serial_number = serial_number;
        this.fullname = fullname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public String getFullname() {
        return fullname;
    }
}
