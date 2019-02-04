package com.rfid.reader.events;

import com.microsoft.azure.storage.StorageException;
import com.rfid.reader.models.LogModel;
import com.rfid.reader.models.ReadModel;
import com.rfid.reader.services.ReaderService;
import com.rfid.reader.util.JsonUtil;
import com.thingmagic.TagReadData;

import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.time.LocalDateTime;

public class ReaderMessageSender implements Runnable {

    @Override
    public void run() {
        try {
            TagReadData[] tagReads;
            while (true) {
                Thread.sleep(1000);
                tagReads = ReaderService.readDataTag(10);
                for (TagReadData tr : tagReads) {
                    if (!tr.epcString().isEmpty()) {
                        ReadModel readModel = new ReadModel(
                                InetAddress.getLocalHost().getHostAddress(),
                                tr.epcString(),
                                tr.getAntenna(),
                                LocalDateTime.now().toString());
                        //TODO: Refactory method for IoT Hub
                        //QueueStorageService.insertReadQueue(new JsonUtil().serialize(readModel));
                    }
                }
            }
        } catch (Exception e) {
            try {
                synchronizeException(e);
            } catch (URISyntaxException | StorageException | UnknownHostException | InvalidKeyException se) {
                se.printStackTrace();
            }
        }
    }

    private static void synchronizeException(Exception exception) throws URISyntaxException, StorageException, UnknownHostException, InvalidKeyException {
        LogModel logModel = new LogModel(
                InetAddress.getLocalHost().getHostAddress(),
                exception.getMessage(),
                exception.getStackTrace().toString(),
                LocalDateTime.now().toString());
        //TODO: Refactory method for IoT Hub
        //QueueStorageService.insertLogQueue(new JsonUtil().serialize(logModel));
    }
}
