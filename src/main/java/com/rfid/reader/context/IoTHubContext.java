package com.rfid.reader.context;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

import java.net.URISyntaxException;

public class IoTHubContext {

    private static final String CONNECTION_STRING =
            "HostName=rfid-iot-hub.azure-devices.net;" +
                    "DeviceId=DeviceRfid;" +
                    "SharedAccessKey=9KVOsdfhqEnGObK1+jSmZ50SJaETd4dZyAStTZUGHag=";
    private static IotHubClientProtocol iotHubClientProtocol = IotHubClientProtocol.AMQPS;
    private static DeviceClient deviceClient;

    private IoTHubContext() {
    }

    public static DeviceClient getDeviceClient() throws URISyntaxException {
        if (deviceClient == null) {
            deviceClient = new DeviceClient(CONNECTION_STRING, iotHubClientProtocol);
        }

        return deviceClient;
    }
}
