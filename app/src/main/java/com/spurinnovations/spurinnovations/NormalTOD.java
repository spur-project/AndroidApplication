package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-11.
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
    OBD(101);

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
