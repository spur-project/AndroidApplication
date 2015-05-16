package com.spurinnovations.spurinnovations;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-15.
 */
public class ParsePacket {

    private Map<TODint, Values> dataMap;

    ParsePacket(Map<TODint, Values> dataMap)
    {
        this.dataMap = dataMap;
    }

    public void parseData(byte[] dataBuffer)
    {
        
    }
}
