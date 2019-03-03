package com.rfid.reader.models;

public class LogModel {

    private String ip;
    private String exception;
    private String stackTrace;
    private String exceptionDate;
    private int telemetryType;

    public LogModel(String ip, String exception, String stackTrace, String exceptionDate, int telemetryType) {
        this.ip = ip;
        this.exception = exception;
        this.stackTrace = stackTrace;
        this.exceptionDate = exceptionDate;
        this.telemetryType = telemetryType;
    }
}
