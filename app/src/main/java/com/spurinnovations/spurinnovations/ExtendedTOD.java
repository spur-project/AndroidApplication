package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum that holds the list of extended TODs (2 bytes)
 * @author Manuel 
 */
public enum ExtendedTOD implements TODint{

    OBD(129);

    private int extendedTOD;

    private static Map<Integer, ExtendedTOD> map = new HashMap<Integer, ExtendedTOD>();

    /**
     * adds every value to the map
     */
    static {
        for (ExtendedTOD TODEnum : ExtendedTOD.values()) {
            map.put(TODEnum.extendedTOD, TODEnum);
        }
    }

    /**
     * constructor
     * @param TOD adds values to the enum
     */
    private ExtendedTOD(final int TOD) { extendedTOD = TOD; }

    /**
     * 
     * @param ExtendedTOD int value of the enum
     * @return the actual enum
     */
    public static ExtendedTOD valueOf(int ExtendedTOD) {
        return map.get(ExtendedTOD);
    }

    @Override
    /**
     * It will return the int value for the enum
     */
    public int showByteValue() {
        return extendedTOD;
    }
}
