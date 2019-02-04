package com.rfid.reader.main;

import com.rfid.reader.events.DeviceMessageSender;
import com.rfid.reader.events.ReaderMessageSender;
import com.rfid.reader.services.ReaderService;
import com.rfid.reader.services.TelemetryService;
import com.thingmagic.ReaderException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RFIDReaderMain {
    public static void main(String[] args) throws IOException, URISyntaxException, ReaderException {

        ExecutorService executorServiceReader = Executors.newFixedThreadPool(1);
        ExecutorService executorServiceDevice = Executors.newFixedThreadPool(1);

        try {
            System.out.println("--------- Middleware RFID Reader ---------");

            ReaderService.connectionReader();
            TelemetryService.connectDevice();

            ReaderMessageSender readerMessageSender = new ReaderMessageSender();
            executorServiceReader.execute(readerMessageSender);

            DeviceMessageSender deviceMessageSender = new DeviceMessageSender();
            executorServiceDevice.execute(deviceMessageSender);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());

            executorServiceReader.shutdownNow();
            executorServiceDevice.shutdownNow();

            ReaderService.disconnectionReader();
            TelemetryService.disconnectionDevice();
        }
    }
}
