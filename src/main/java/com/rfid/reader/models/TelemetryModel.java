package com.rfid.reader.models;

public class TelemetryModel {

    public String ip;
    public int temperature;
    public boolean isConnection;

    public TelemetryModel(String ip, int temperature, boolean isConnection) {
        this.ip = ip;
        this.temperature = temperature;
        this.isConnection = isConnection;
    }
}
