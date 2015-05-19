package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that will hold a map for whether a TOD is signed or unsigned
 * @author Manuel
 */
public class signTOD {

    Map<TODint, Integer> signTOD = new HashMap<TODint, Integer>();

    /**
     * Constructor : will populate the map with the default values
     */
    signTOD()
    {

        signTOD.put(NormalTOD.ANDROID_DEVICE_PRIVILEGES, 0);
        signTOD.put(NormalTOD.DEVICE_CONFIGURATION, 0);
        signTOD.put(NormalTOD.CONTROL_STATUS, 0);
        signTOD.put(NormalTOD.CAMERA_STATUS, 0);
        signTOD.put(NormalTOD.VEHICLE_STATUS, 0);
        signTOD.put(NormalTOD.VEHICLE_SPEED, 1);
        signTOD.put(NormalTOD.ACCELERATORPEDAL_ACTUALPERCENT, 0);
        signTOD.put(NormalTOD.ACCELERATORPEDAL_MAXALLOWPERCENT, 0);
        signTOD.put(NormalTOD.VEHICLE_ACCELERATION, 1);
        signTOD.put(NormalTOD.VEHICLE_LONGITUDIONAL_ACC, 1);
        signTOD.put(NormalTOD.VEHICLE_CORNERING_ACCELERATION, 1);
        signTOD.put(NormalTOD.POSTED_SPEED_LIMIT, 0);
        signTOD.put(NormalTOD.ELAPSED_TIME_SPEED_LIMIT, 0);
        signTOD.put(NormalTOD.HEADWAY, 0);
        signTOD.put(NormalTOD.BATTERY_VOLTAGE, 0);
        signTOD.put(NormalTOD.LATITUDE,  1);
        signTOD.put(NormalTOD.LONGITUDE, 1);
        signTOD.put(NormalTOD.ALTITUDE, 1);
        signTOD.put(NormalTOD.SOFTWARE_REVISION, 0);
        signTOD.put(NormalTOD.ACCELERATOR_PEDAL_CONFIG, 0);
        signTOD.put(NormalTOD.BRAKE_PEDAL_PERCENT, 0);
        signTOD.put(NormalTOD.ODOMETER, 0);
        /*signTOD.put(obdTOD.PIDS_SUPPORTED_1_20, 0);
        signTOD.put(obdTOD.MONITOR_DTC, 0);
        signTOD.put(obdTOD.FREEZE_DTC, 0);
        signTOD.put(obdTOD.FUEL_SYSTEM_STATUS, 0);
        signTOD.put(obdTOD.ENGINE_LOAD, 1);
        signTOD.put(obdTOD.COOLANT_TEMPERATURE, 1);
        signTOD.put(obdTOD.SHORT_TERM_FUEL_B1, 1);
        signTOD.put(obdTOD.LONG_TERM_FUEL_B1, 1);
        signTOD.put(obdTOD.SHORT_TERM_FUEL_B2, 1);
        signTOD.put(obdTOD.LONG_TERM_FUEL_B2, 1);
        signTOD.put(obdTOD.FUEL_PRESSURE, 1);
        signTOD.put(obdTOD.MANIFOLD_PRESSURE, 1);
        signTOD.put(obdTOD.ENGINE_RPM, 0);
        signTOD.put(obdTOD.VEHICLE_SPEED, 1);
        signTOD.put(obdTOD.TIMING_ADVANCE, 1);
        signTOD.put(obdTOD.AIR_TEMPERATURE, 1);
        signTOD.put(obdTOD.MAF_AIRFLOW, 0);
        signTOD.put(obdTOD.THROTTLE_POSITION, 1);
        signTOD.put(obdTOD.SECONDARY_AIR_STATUS, 1);
        signTOD.put(obdTOD.OXYGEN_SENSORS, 1);
        signTOD.put(obdTOD.B1_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B1_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B1_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B1_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B2_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B2_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B2_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.B2_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        signTOD.put(obdTOD.OBD_STANDARD, 1);
        signTOD.put(obdTOD.OXYGEN_SENSORS_SECOND, 1);
        signTOD.put(obdTOD.AUX_INPUT_STATUS, 1);
        signTOD.put(obdTOD.RUNTIME_ENGINE, 0);
        signTOD.put(obdTOD.PIDS_SUPPORTED_21_40, 0);
        signTOD.put(obdTOD.DISTANCE_MIL_ON, 0);
        signTOD.put(obdTOD.FUEL_RAIL_PRESSURE_VACUUM, 0);
        signTOD.put(obdTOD.FUEL_RAIL_PRESSURE_INJECTION, 0);
        signTOD.put(obdTOD.O2S1_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S2_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S3_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S4_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S5_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S6_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S7_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.O2S8_WR_LAMBDA_RATIO_VOLTAGE, 0);
        signTOD.put(obdTOD.COMMANDED_EGR, 1);
        signTOD.put(obdTOD.EGR_ERROR, 1);
        signTOD.put(obdTOD.COMMANDED_EVA_PURGE, 1);
        signTOD.put(obdTOD.FUEL_LEVEL_INPUT, 1);
        signTOD.put(obdTOD.NUMBER_WARMUPS, 1);
        signTOD.put(obdTOD.DISTANCE_TRAVELED, 0);
        signTOD.put(obdTOD.EV_VAPOR_PRESSURE, 0);
        signTOD.put(obdTOD.BAROMETRIC_PRESSURE, 1);
        signTOD.put(obdTOD.O2S1_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S2_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S3_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S4_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S5_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S6_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S7_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.O2S8_WR_LAMBDA_RATIO_CURRENT, 0);
        signTOD.put(obdTOD.CATALYST_TEMP_B1S1, 0);
        signTOD.put(obdTOD.CATALYST_TEMP_B2S1, 0);
        signTOD.put(obdTOD.CATALYST_TEMP_B1S2, 0);
        signTOD.put(obdTOD.CATALYST_TEMP_B2S2, 0);
        signTOD.put(obdTOD.PIDS_SUPPORTED_41_60, 0);
        signTOD.put(obdTOD.MONITOR_STATUS, 0);
        signTOD.put(obdTOD.CONTROL_MODULE_VOLTAGE, 0);
        signTOD.put(obdTOD.ABSOLUTE_LOAD_VALUE, 0);
        signTOD.put(obdTOD.FUEL_AIR_RATIO, 0);
        signTOD.put(obdTOD.RELATIVE_THROTTLE_POSITION, 1);
        signTOD.put(obdTOD.AMBIENT_AIR_PRESSURE, 1);
        signTOD.put(obdTOD.ABSOLUTE_THROTTLE_POSITION_B, 1);
        signTOD.put(obdTOD.ABSOLUTE_THROTTLE_POSITION_C, 1);
        signTOD.put(obdTOD.ACC_PEDAL_POSITION_D, 1);
        signTOD.put(obdTOD.ACC_PEDAL_POSITION_E, 1);
        signTOD.put(obdTOD.ACC_PEDAL_POSITION_F, 1);
        signTOD.put(obdTOD.COMMANDED_THROTTLE_ACTUATOR, 1);
        signTOD.put(obdTOD.TIME_MIL_ON, 0);
        signTOD.put(obdTOD.TIME_TROUBLE_CODES_CLEAR, 0);
        signTOD.put(obdTOD.MAXVAL_EQRATIO_OSVOLTAGE_OSCURRENT_IMPRESSURE, 0);
        signTOD.put(obdTOD.MAXVAL_AIRFLOW_RATE, 0);
        signTOD.put(obdTOD.FUEL_TYPE, 1);
        signTOD.put(obdTOD.ETHANOL_PERCENTAGE, 1);
        signTOD.put(obdTOD.ABSOLUTE_EVAP_PRESSURE, 0);
        signTOD.put(obdTOD.EVAP_PRESSURE, 0);
        signTOD.put(obdTOD.ST_SECONDARY_SENSOR_B1_B3, 0);
        signTOD.put(obdTOD.LT_SECONDARY_SENSOR_B1_B3, 0);
        signTOD.put(obdTOD.ST_SECONDARY_SENSOR_B2_B4, 0);
        signTOD.put(obdTOD.LT_SECONDARY_SENSOR_B2_B4, 0);
        signTOD.put(obdTOD.FUEL_RAIL_PRESSURE, 0);
        signTOD.put(obdTOD.RELATIVE_ACC_PEDAL_POSITION, 1);
        signTOD.put(obdTOD.HYBRID_BATTLE_REMAINING_LIFE, 1);
        signTOD.put(obdTOD.ENGINE_OIL_TEMPERATURE, 1);
        signTOD.put(obdTOD.FUEL_INJECTION_TIMING, 0);
        signTOD.put(obdTOD.ENGINE_FUEL_RATE, 0);
        signTOD.put(obdTOD.EMISSION_REQUIREMENTS, 1);
        signTOD.put(obdTOD.PIDS_SUPPORTED_61_80, 0);
        signTOD.put(obdTOD.DRIVER_DEMAND_PERCENT_TORQUE, 1);
        signTOD.put(obdTOD.ACTUAL_ENGINE_PERCENT_TORQUE, 1);
        signTOD.put(obdTOD.ENGINE_REFERENCE_TORQUE, 0);
        signTOD.put(obdTOD.ENGINE_PERCENT_TORQUE_DATA, 0);
        signTOD.put(obdTOD.AUX_IN_OUT_SUPPORTED, 0);*/

    }

    /**
     *
     * @return map with the values.
     */
    Map<TODint, Integer> getsignTOD()
    {
        return signTOD;
    }
}
