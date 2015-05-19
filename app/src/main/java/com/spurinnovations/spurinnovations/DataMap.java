package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that will hold a static parsePacket object as well as a static Map
 * This object is global and a singleton.
 * @author Manuel
 */
public class DataMap {

    private static Map<TODint, String> map;
    private static ParsePacket parsingPacket;

    /**
     *
     * @return map of string values
     */
    public static synchronized Map<TODint, String> getMap(){
        return map;
    }

    /**
     *
     * @param map map to set for the dataMap object
     */
    public static synchronized void setMap(Map<TODint, String> map){
        DataMap.map = map;
    }

    /**
     *
     * @param parsingPacket parsingPacket object to set for the dataMap object
     */
    public static synchronized void setParsingPacket(ParsePacket parsingPacket) {
        DataMap.parsingPacket = parsingPacket;
    }

    /**
     *
     * @return parsingpacket object
     */
    public static synchronized ParsePacket getParsingPacket()
    {
        return parsingPacket;
    }
}
