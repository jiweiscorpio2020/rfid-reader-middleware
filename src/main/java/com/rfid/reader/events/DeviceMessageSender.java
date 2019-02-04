package com.rfid.reader.events;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;
import com.microsoft.azure.sdk.iot.device.Message;
import com.rfid.reader.models.TelemetryModel;
import com.rfid.reader.services.ReaderService;
import com.rfid.reader.services.TelemetryService;
import com.rfid.reader.util.JsonUtil;
import com.thingmagic.ReaderException;

import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class DeviceMessageSender implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TelemetryModel telemetryModel = new TelemetryModel(
                        InetAddress.getLocalHost().getHostAddress(),
                        ReaderService.getTemperature(),
                        ReaderService.isConnection()
                );

                String telemetryJson = new JsonUtil().serialize(telemetryModel);
                Message msg = new Message(telemetryJson);

                System.out.println("Sending message: " + telemetryJson);

                Object lock = new Object();
                DeviceEventCallback callback = new DeviceEventCallback();

                TelemetryService.sendEventDeviceAsync(msg, callback, lock);

                synchronized (lock) {
                    lock.wait();
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException | UnknownHostException | URISyntaxException | ReaderException e) {
            System.out.println("Interrupted telemetry");
            e.printStackTrace();
        }
    }

    private static class DeviceEventCallback implements IotHubEventCallback {
        public void execute(IotHubStatusCode status, Object context) {
            System.out.println("IoT Hub responded to message with status: " + status.name());

            if (context != null) {
                synchronized (context) {
                    context.notify();
                }
            }
        }
    }
}
