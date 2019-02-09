package com.rfid.reader.models;

public class TelemetryModel {

    public String ip;
    public int temperature;
    public int telemetryType;
    public boolean isConnection;

    public TelemetryModel(String ip, int temperature, int telemetryType, boolean isConnection) {
        this.ip = ip;
        this.temperature = temperature;
        this.telemetryType = telemetryType;
        this.isConnection = isConnection;
    }
}
