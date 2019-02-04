package com.rfid.reader.context;

import com.thingmagic.*;

public class ReaderContext {

    private static final String READER_URI = "tmr:///";
    private static final String READER_PORT = "COM3";
    private static Reader reader;

    private ReaderContext() {
    }

    public static Reader getReader() throws ReaderException {
        if (reader == null) {
            reader = Reader.create(READER_URI + READER_PORT);
        }

        return reader;
    }
}
