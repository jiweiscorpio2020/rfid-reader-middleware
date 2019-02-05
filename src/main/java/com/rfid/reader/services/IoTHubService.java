package com.rfid.reader.services;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.Message;
import com.rfid.reader.context.IoTHubContext;

import java.io.IOException;
import java.net.URISyntaxException;

public class IoTHubService {

    public static void connectDevice() throws IOException, URISyntaxException {
        IoTHubContext.getDeviceClient().open();
    }

    public static void disconnectionDevice() throws IOException, URISyntaxException {
        IoTHubContext.getDeviceClient().closeNow();
    }

    public static void sendEventDeviceAsync(Message message, IotHubEventCallback callback, Object callbackContext) throws URISyntaxException {
        IoTHubContext.getDeviceClient().sendEventAsync(message, callback, callbackContext);
    }
}
