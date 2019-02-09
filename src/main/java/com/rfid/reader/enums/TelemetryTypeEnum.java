package com.rfid.reader.enums;

public enum  TelemetryTypeEnum {
    TELEMETRY(1),
    READ(2),
    LOG(3);

    private final int enumValue;

    TelemetryTypeEnum(int enumValue) {
        this.enumValue = enumValue;
    }

    public int getEnumValue(){
        return this.enumValue;
    }
}