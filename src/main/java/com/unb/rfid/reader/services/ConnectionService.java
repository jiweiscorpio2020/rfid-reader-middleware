package com.unb.rfid.reader.services;

import com.microsoft.azure.storage.StorageException;
import com.thingmagic.*;
import com.unb.rfid.reader.models.AntennaModel;
import com.unb.rfid.reader.models.LogModel;
import com.unb.rfid.reader.repository.AzureRepository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConnectionService {

    private static final String READER_URI = "tmr:///";
    private static final String READER_PORT = "COM4";
    private static Reader reader;

    public void connectionReader() throws ReaderException, URISyntaxException, InvalidKeyException, StorageException {
        int[] antennaList = new int[]{1};

        reader = Reader.create(READER_URI + READER_PORT);
        reader.connect();

        Reader.Region[] supportedRegions = (Reader.Region[]) reader.paramGet(TMConstants.TMR_PARAM_REGION_SUPPORTEDREGIONS);
        reader.paramSet("/reader/region/id", supportedRegions[0]);

        SimpleReadPlan plan = new SimpleReadPlan(antennaList, TagProtocol.GEN2, null, null, 1000);
        reader.paramSet(TMConstants.TMR_PARAM_READ_PLAN, plan);

        synchronizeRead();
    }

    public void disconnectionReader() {
        reader.stopReading();
        reader.destroy();
    }

    private void synchronizeRead() throws URISyntaxException, InvalidKeyException, StorageException {
        try {
            TagReadData[] tagReads;
            while (true) {
                Thread.sleep(2000);
                tagReads = reader.read(10);
                for (TagReadData tr : tagReads) {
                    if (!tr.epcString().isEmpty()) {
                        AntennaModel antennaModel = new AntennaModel(
                                READER_PORT,
                                UUID.randomUUID(),
                                tr.epcString(),
                                tr.getAntenna(),
                                LocalDateTime.now().toString());
                        new AzureRepository().insertTableAntenna(antennaModel);
                    }
                }
            }
        } catch (Exception e) {
            synchronizeException(e);
        }
    }

    private void synchronizeException(Exception exception) throws URISyntaxException, InvalidKeyException, StorageException {
        LogModel logModel = new LogModel(
                READER_PORT,
                UUID.randomUUID(),
                exception.getMessage(),
                exception.getStackTrace().toString(),
                LocalDateTime.now().toString());
        new AzureRepository().insertTableLog(logModel);
    }
}
