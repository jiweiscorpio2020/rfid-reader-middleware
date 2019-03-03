package com.rfid.reader.models;

public class TelemetryModel {

    private String ip;
    private int temperature;
    private int telemetryType;
    private boolean isConnection;

    public TelemetryModel(String ip, int temperature, int telemetryType, boolean isConnection) {
        this.ip = ip;
        this.temperature = temperature;
        this.telemetryType = telemetryType;
        this.isConnection = isConnection;
    }
}
