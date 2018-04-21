package com.unb.rfid.reader.context;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class AzureContext {
    private final String CONNECTION_STRING =
            "DefaultEndpointsProtocol=https;AccountName={STORAGE};" +
                    "AccountKey={KEY};" +
                    "EndpointSuffix=core.windows.net";
    private static CloudTableClient cloudTableClient;

    public AzureContext() throws URISyntaxException, InvalidKeyException {
        if (cloudTableClient == null) {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(CONNECTION_STRING);
            cloudTableClient = storageAccount.createCloudTableClient();
        }
    }

    public CloudTable Antenna() throws URISyntaxException, StorageException {
        return cloudTableClient.getTableReference("Antenna");
    }

    public CloudTable Log() throws URISyntaxException, StorageException {
        return cloudTableClient.getTableReference("Log");
    }
}
