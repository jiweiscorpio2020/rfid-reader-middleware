package com.rfid.reader.models;

public class ReadModel {

    private String ip;
    private String epc;
    private String readDate;
    private int antenna;
    private int telemetryType;

    public ReadModel(String ip, String epc, String readDate, int antenna, int telemetryType) {
        this.ip = ip;
        this.epc = epc;
        this.readDate = readDate;
        this.antenna = antenna;
        this.telemetryType = telemetryType;
    }
}
