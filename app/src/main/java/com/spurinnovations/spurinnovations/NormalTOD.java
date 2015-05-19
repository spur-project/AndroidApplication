package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum that holds the list of Normal TODs (1 byte)
 * @author Manuel
 */
public enum NormalTOD implements TODint
{
    ANDROID_DEVICE_PRIVILEGES(3),
    DEVICE_CONFIGURATION(5),
    CONTROL_STATUS(50),
    CAMERA_STATUS(51),
    VEHICLE_STATUS(52),
    VEHICLE_SPEED(53),
    ACCELERATORPEDAL_ACTUALPERCENT(54),
    ACCELERATORPEDAL_MAXALLOWPERCENT(55),
    VEHICLE_ACCELERATION(56),
    VEHICLE_LONGITUDIONAL_ACC(57),
    VEHICLE_CORNERING_ACCELERATION(58),
    POSTED_SPEED_LIMIT(59),
    ELAPSED_TIME_SPEED_LIMIT(60),
    HEADWAY(61),
    BATTERY_VOLTAGE(62),
    LATITUDE(63),
    LONGITUDE(64),
    ALTITUDE(65),
    SOFTWARE_REVISION(66),
    ACCELERATOR_PEDAL_CONFIG(67),
    BRAKE_PEDAL_PERCENT(68),
    ODOMETER(69),
    OBD(255);

    private int normalTOD;

    private static Map<Integer, NormalTOD> map = new HashMap<Integer, NormalTOD>();

    /**
     * adds every value to the map
     */
    static {
        for (NormalTOD TODEnum : NormalTOD.values()) {
            map.put(TODEnum.normalTOD, TODEnum);
        }
    }

    /**
     * add every value to the enum
     * @param TOD enum value
     */
    private NormalTOD(final int TOD) { normalTOD = TOD; }

    /**
     * 
     * @param NormalTOD integer value fo the enum
     * @return actual enum
     */
    public static NormalTOD valueOf(int NormalTOD) {
        return map.get(NormalTOD);
    }

    /**
     * 
     * @return integer value of the enum
     */
    public int showByteValue()
    {
        return normalTOD;
    }
}
