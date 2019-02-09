package com.rfid.reader.events;

import com.microsoft.azure.sdk.iot.device.Message;
import com.rfid.reader.enums.TelemetryTypeEnum;
import com.rfid.reader.models.LogModel;
import com.rfid.reader.models.ReadModel;
import com.rfid.reader.services.IoTHubService;
import com.rfid.reader.services.ReaderService;
import com.rfid.reader.util.JsonUtil;
import com.thingmagic.TagReadData;

import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
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
                                LocalDateTime.now().toString(),
                                tr.getAntenna(),
                                TelemetryTypeEnum.READ.getEnumValue());

                        String readJson = new JsonUtil().serialize(readModel);
                        Message msg = new Message(readJson);

                        Object lock = new Object();
                        DeviceMessageSender.DeviceEventCallback callback = new DeviceMessageSender.DeviceEventCallback();

                        IoTHubService.sendEventDeviceAsync(msg, callback, lock);

                        synchronized (lock) {
                            lock.wait();
                        }

                        System.out.println("Sending message Read: " + readJson);
                    }
                }
            }
        } catch (Exception e) {
            try {
                synchronizeException(e);
            } catch (URISyntaxException | UnknownHostException se) {
                se.printStackTrace();
            }
        }
    }

    private static void synchronizeException(Exception exception) throws URISyntaxException, UnknownHostException {
        LogModel logModel = new LogModel(
                InetAddress.getLocalHost().getHostAddress(),
                exception.getMessage(),
                exception.getStackTrace().toString(),
                LocalDateTime.now().toString(),
                TelemetryTypeEnum.LOG.getEnumValue());

        String logJson = new JsonUtil().serialize(logModel);
        Message msg = new Message(logJson);

        Object lock = new Object();
        DeviceMessageSender.DeviceEventCallback callback = new DeviceMessageSender.DeviceEventCallback();

        IoTHubService.sendEventDeviceAsync(msg, callback, lock);

        System.out.println("Sending message Log: " + logJson);
    }
}
