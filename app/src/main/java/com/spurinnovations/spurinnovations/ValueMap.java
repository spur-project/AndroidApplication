package com.spurinnovations.spurinnovations;

import java.util.Map;

/**
 * singleton and global object to hold the value pair for a ByteToD object
 * @author Manuel
 * @deprecated
 */
public class ValueMap {

    private static Map<TODint, Integer> map;

    /**
     *
     * @return map of values
     */
    public static synchronized Map<TODint, Integer> getMap(){
        return map;
    }

    /**
     *
     * @param map map to set for the ValueMap object.
     */
    public static synchronized void setMap(Map<TODint, Integer> map){
        ValueMap.map = map;
    }

}
