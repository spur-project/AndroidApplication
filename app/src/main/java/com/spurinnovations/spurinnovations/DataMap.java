package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-12.
 */
public class DataMap {

    private static Map<TODint, Values> map;

    public static synchronized Map<TODint, Values> getMap(){
        return map;
    }
    public static synchronized void setMap(Map<TODint, Values> map){
        DataMap.map = map;
    }
}
