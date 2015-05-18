package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-12.
 */
public class DataMap {

    private static Map<TODint, String> map;
    private static ParsePacket parsingPacket;

    public static synchronized Map<TODint, String> getMap(){
        return map;
    }
    public static synchronized void setMap(Map<TODint, String> map){
        DataMap.map = map;
    }
    public static synchronized void setParsingPacket(ParsePacket parsingPacket) {
        DataMap.parsingPacket = parsingPacket;
    }
    public static synchronized ParsePacket getParsingPacket()
    {
        return parsingPacket;
    }
}
