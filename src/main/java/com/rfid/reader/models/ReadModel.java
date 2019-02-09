package com.rfid.reader.models;

public class ReadModel {

    public String ip;
    public String epc;
    public String readDate;
    public int antenna;
    public int telemetryType;

    public ReadModel(String ip, String epc, String readDate, int antenna, int telemetryType) {
        this.ip = ip;
        this.epc = epc;
        this.readDate = readDate;
        this.antenna = antenna;
        this.telemetryType = telemetryType;
    }
}
