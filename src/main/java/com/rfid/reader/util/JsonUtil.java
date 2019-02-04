package com.rfid.reader.util;

import com.google.gson.Gson;

public class JsonUtil {

    public String serialize(Object value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
