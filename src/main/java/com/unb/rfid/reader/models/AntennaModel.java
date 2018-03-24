package com.unb.rfid.reader.models;

import com.microsoft.azure.storage.table.TableServiceEntity;

import java.util.UUID;

public class AntennaModel extends TableServiceEntity {

    private String epc;
    private String ip;
    private int antenna;
    private String readDate;

    public AntennaModel(String ip, UUID guid, String epc, int antenna, String readDate) {
        this.partitionKey = ip;
        this.rowKey = guid.toString();
        this.epc = epc;
        this.ip = ip;
        this.antenna = antenna;
        this.readDate = readDate;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAntenna() {
        return antenna;
    }

    public void setAntenna(int antenna) {
        this.antenna = antenna;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }
}
