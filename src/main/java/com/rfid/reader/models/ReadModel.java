package com.rfid.reader.models;

public class ReadModel {

    public String epc;
    public String ip;
    public int antenna;
    public String readDate;

    public ReadModel(String ip, String epc, int antenna, String readDate) {
        this.ip = ip;
        this.epc = epc;
        this.ip = ip;
        this.antenna = antenna;
        this.readDate = readDate;
    }
}
