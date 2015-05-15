package com.spurinnovations.spurinnovations;

import java.util.Map;

/**
 * Created by Manuel on 2015-05-12.
 */
public class ValueMap {

    private static Map<TODint, Integer> map;

    public static synchronized Map<TODint, Integer> getMap(){
        return map;
    }
    public static synchronized void setMap(Map<TODint, Integer> map){
        ValueMap.map = map;
    }

}
