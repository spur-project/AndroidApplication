package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-11.
 */
public enum NormalTOD implements TODint
{
    SPEED(0),
    ACCELERATION(1),
    BREAKING(2),
    CORNERING(3),
    SPEED_LIMIT(4),
    IMPACT_TIME(5),
    IMPACT_OBJECT(6),
    OBD(255);

    private int normalTOD;

    private static Map<Integer, NormalTOD> map = new HashMap<Integer, NormalTOD>();

    static {
        for (NormalTOD legEnum : NormalTOD.values()) {
            map.put(legEnum.normalTOD, legEnum);
        }
    }

    private NormalTOD(final int leg) { normalTOD = leg; }

    public static NormalTOD valueOf(int NormalTOD) {
        return map.get(NormalTOD);
    }
}
