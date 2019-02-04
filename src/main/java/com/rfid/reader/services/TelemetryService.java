package com.rfid.reader.services;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.Message;
import com.rfid.reader.context.TelemetryContext;

import java.io.IOException;
import java.net.URISyntaxException;

public class TelemetryService {

    public static void connectDevice() throws IOException, URISyntaxException {
        TelemetryContext.getDeviceClient().open();
    }

    public static void disconnectionDevice() throws IOException, URISyntaxException {
        TelemetryContext.getDeviceClient().closeNow();
    }

    public static void sendEventDeviceAsync(Message message, IotHubEventCallback callback, Object callbackContext) throws URISyntaxException {
        TelemetryContext.getDeviceClient().sendEventAsync(message, callback, callbackContext);
    }
}
