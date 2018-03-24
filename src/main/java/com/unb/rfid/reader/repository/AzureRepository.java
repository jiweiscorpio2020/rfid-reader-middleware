package com.unb.rfid.reader.repository;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.*;
import com.unb.rfid.reader.models.AntennaModel;
import com.unb.rfid.reader.models.LogModel;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class AzureRepository {
    private static final String CONNECTION_STRING =
            "DefaultEndpointsProtocol=https;AccountName=unbstoragedev;" +
                    "AccountKey=fKJppncHugzneTpp7tc7jOdaL4MFvnqmcqIpYdgt64TkKj14jbWUFpw/yAXIPo4ZKQeHzsdE6/WVhj1L4dRlIQ==;" +
                    "EndpointSuffix=core.windows.net";
    private static CloudTableClient cloudTableClient;

    public AzureRepository() throws URISyntaxException, InvalidKeyException {
        if (cloudTableClient == null) {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(CONNECTION_STRING);
            cloudTableClient = storageAccount.createCloudTableClient();
        }
    }

    public void insertTableAntenna(AntennaModel antennaModel) throws URISyntaxException, StorageException {
        CloudTable table = cloudTableClient.getTableReference("Antenna");
        TableOperation tableOperation = TableOperation.insert(antennaModel);
        table.execute(tableOperation);
    }

    public void insertTableLog(LogModel logModel) throws URISyntaxException, StorageException {
        CloudTable table = cloudTableClient.getTableReference("Log");
        TableOperation tableOperation = TableOperation.insert(logModel);
        table.execute(tableOperation);
    }
}
