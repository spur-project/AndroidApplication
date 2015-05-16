package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-15.
 */
public enum ExtendedTOD implements TODint{

    OBD(129);

    private int extendedTOD;

    private static Map<Integer, ExtendedTOD> map = new HashMap<Integer, ExtendedTOD>();

    static {
        for (ExtendedTOD legEnum : ExtendedTOD.values()) {
            map.put(legEnum.extendedTOD, legEnum);
        }
    }

    private ExtendedTOD(final int leg) { extendedTOD = leg; }

    public static ExtendedTOD valueOf(int ExtendedTOD) {
        return map.get(ExtendedTOD);
    }
}
