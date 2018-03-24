package com.unb.rfid.reader.models;

import com.microsoft.azure.storage.table.TableServiceEntity;

import java.util.UUID;

public class LogModel extends TableServiceEntity {

    private String ip;
    private String exception;
    private String stackTrace;
    private String exceptionDate;

    public LogModel(String ip, UUID guid, String exception, String stackTrace, String exceptionDate) {
        this.partitionKey = ip;
        this.rowKey = guid.toString();
        this.exception = exception;
        this.stackTrace = stackTrace;
        this.exceptionDate = exceptionDate;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void setExceptionDate(String exceptionDate) {
        this.exceptionDate = exceptionDate;
    }
}
