package com.rfid.reader.repository;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.*;
import com.rfid.reader.context.AzureContext;
import com.rfid.reader.models.AntennaModel;
import com.rfid.reader.models.LogModel;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class AzureRepository {

    private AzureContext azureContext;

    public AzureRepository() throws URISyntaxException, InvalidKeyException {
        this.azureContext = new AzureContext();
    }

    public void insertTableAntenna(AntennaModel antennaModel) throws URISyntaxException, StorageException {
        CloudTable table = azureContext.Antenna();
        TableOperation tableOperation = TableOperation.insert(antennaModel);
        table.execute(tableOperation);
    }

    public void insertTableLog(LogModel logModel) throws URISyntaxException, StorageException {
        CloudTable table = azureContext.Log();
        TableOperation tableOperation = TableOperation.insert(logModel);
        table.execute(tableOperation);
    }
}
