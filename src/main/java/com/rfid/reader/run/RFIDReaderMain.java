package com.rfid.reader.run;

import com.rfid.reader.services.ConnectionService;

public class RFIDReaderMain {
    public static void main(String[] args) {
        ConnectionService connectionService = new ConnectionService();
        try {
            connectionService.connectionReader();
        } catch (Exception ex) {
            connectionService.disconnectionReader();
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
    }
}
