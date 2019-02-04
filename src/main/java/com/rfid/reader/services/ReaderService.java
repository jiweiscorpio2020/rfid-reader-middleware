package com.rfid.reader.services;

import com.rfid.reader.context.ReaderContext;
import com.thingmagic.*;

public class ReaderService {

    public static void connectionReader() throws ReaderException {
        ReaderContext.getReader().connect();
        setConfigurationReader();
    }

    public static void disconnectionReader() throws ReaderException {
        ReaderContext.getReader().stopReading();
        ReaderContext.getReader().destroy();
    }

    public static TagReadData[] readDataTag(long count) throws ReaderException {
        Reader reader = ReaderContext.getReader();
        return reader.read(count);
    }

    public static int getTemperature() throws ReaderException {
        return (int) ReaderContext.getReader().paramGet("/reader/radio/temperature");
    }

    public static boolean isConnection() throws ReaderException {
        return ((int) ReaderContext.getReader().paramGet("/reader/radio/temperature")) > 0;
    }

    private static void setConfigurationReader() throws ReaderException {
        Reader.Region[] supportedRegions = (Reader.Region[]) ReaderContext.getReader().paramGet(TMConstants.TMR_PARAM_REGION_SUPPORTEDREGIONS);
        ReaderContext.getReader().paramSet("/reader/region/id", supportedRegions[0]);

        SimpleReadPlan plan = new SimpleReadPlan(new int[]{1}, TagProtocol.GEN2, null, null, 1000);
        ReaderContext.getReader().paramSet(TMConstants.TMR_PARAM_READ_PLAN, plan);
    }
}
