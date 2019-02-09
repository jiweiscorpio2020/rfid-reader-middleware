package com.rfid.reader.models;

public class LogModel {

    public String ip;
    public String exception;
    public String stackTrace;
    public String exceptionDate;
    public int telemetryType;

    public LogModel(String ip, String exception, String stackTrace, String exceptionDate, int telemetryType) {
        this.ip = ip;
        this.exception = exception;
        this.stackTrace = stackTrace;
        this.exceptionDate = exceptionDate;
        this.telemetryType = telemetryType;
    }
}
