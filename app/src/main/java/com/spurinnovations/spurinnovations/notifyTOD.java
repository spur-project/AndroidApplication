package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that will hold a map that will be updated if a piece of data has been received
 * for the specified TOD.
 * @author Manuel
 */
public class notifyTOD {

    Map<TODint, Integer> notifyTOD = new HashMap<TODint, Integer>();

    /**
     * Constructor : will populate the map with all 0 since no data has been
     * received for any TOD.
     */
    notifyTOD()
    {

        notifyTOD.put(NormalTOD.ANDROID_DEVICE_PRIVILEGES, 0);
        notifyTOD.put(NormalTOD.DEVICE_CONFIGURATION, 0);
        notifyTOD.put(NormalTOD.CONTROL_STATUS, 0);
        notifyTOD.put(NormalTOD.CAMERA_STATUS, 0);
        notifyTOD.put(NormalTOD.VEHICLE_STATUS, 0);
        notifyTOD.put(NormalTOD.VEHICLE_SPEED, 0);
        notifyTOD.put(NormalTOD.ACCELERATORPEDAL_ACTUALPERCENT, 0);
        notifyTOD.put(NormalTOD.ACCELERATORPEDAL_MAXALLOWPERCENT, 0);
        notifyTOD.put(NormalTOD.VEHICLE_ACCELERATION, 0);
        notifyTOD.put(NormalTOD.VEHICLE_LONGITUDIONAL_ACC, 0);
        notifyTOD.put(NormalTOD.VEHICLE_CORNERING_ACCELERATION, 0);
        notifyTOD.put(NormalTOD.POSTED_SPEED_LIMIT, 0);
        notifyTOD.put(NormalTOD.ELAPSED_TIME_SPEED_LIMIT, 0);
        notifyTOD.put(NormalTOD.HEADWAY, 0);
        notifyTOD.put(NormalTOD.BATTERY_VOLTAGE, 0);
        notifyTOD.put(NormalTOD.LATITUDE, 0);
        notifyTOD.put(NormalTOD.LONGITUDE, 0);
        notifyTOD.put(NormalTOD.ALTITUDE, 0);
        notifyTOD.put(NormalTOD.SOFTWARE_REVISION, 0);
        notifyTOD.put(NormalTOD.ACCELERATOR_PEDAL_CONFIG, 0);
        notifyTOD.put(NormalTOD.BRAKE_PEDAL_PERCENT, 0);
        notifyTOD.put(NormalTOD.ODOMETER, 0);
        notifyTOD.put(obdTOD.PIDS_SUPPORTED_1_20, 0);
        notifyTOD.put(obdTOD.MONITOR_DTC, 0);
        notifyTOD.put(obdTOD.FREEZE_DTC, 0);
        notifyTOD.put(obdTOD.FUEL_SYSTEM_STATUS, 0);
        notifyTOD.put(obdTOD.ENGINE_LOAD, 0);
        notifyTOD.put(obdTOD.COOLANT_TEMPERATURE, 0);
        notifyTOD.put(obdTOD.SHORT_TERM_FUEL_B1, 0);
        notifyTOD.put(obdTOD.LONG_TERM_FUEL_B1, 0);
        notifyTOD.put(obdTOD.SHORT_TERM_FUEL_B2, 0);
        notifyTOD.put(obdTOD.LONG_TERM_FUEL_B2, 0);
        notifyTOD.put(obdTOD.FUEL_PRESSURE, 0);
        notifyTOD.put(obdTOD.MANIFOLD_PRESSURE, 0);
        notifyTOD.put(obdTOD.ENGINE_RPM, 0);
        notifyTOD.put(obdTOD.VEHICLE_SPEED, 0);
        notifyTOD.put(obdTOD.TIMING_ADVANCE, 0);
        notifyTOD.put(obdTOD.AIR_TEMPERATURE, 0);
        notifyTOD.put(obdTOD.MAF_AIRFLOW, 0);
        notifyTOD.put(obdTOD.THROTTLE_POSITION, 0);
        notifyTOD.put(obdTOD.SECONDARY_AIR_STATUS, 0);
        notifyTOD.put(obdTOD.OXYGEN_SENSORS, 0);
        notifyTOD.put(obdTOD.B1_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B1_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B1_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B1_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B2_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B2_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B2_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.B2_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 0);
        notifyTOD.put(obdTOD.OBD_STANDARD, 0);
        notifyTOD.put(obdTOD.OXYGEN_SENSORS_SECOND, 0);
        notifyTOD.put(obdTOD.AUX_INPUT_STATUS, 0);
        notifyTOD.put(obdTOD.RUNTIME_ENGINE, 0);
        notifyTOD.put(obdTOD.PIDS_SUPPORTED_21_40, 0);
        notifyTOD.put(obdTOD.DISTANCE_MIL_ON, 0);
        notifyTOD.put(obdTOD.FUEL_RAIL_PRESSURE_VACUUM, 0);
        notifyTOD.put(obdTOD.FUEL_RAIL_PRESSURE_INJECTION, 0);
        notifyTOD.put(obdTOD.O2S1_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S2_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S3_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S4_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S5_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S6_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S7_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.O2S8_WR_LAMBDA_RATIO_VOLTAGE, 0);
        notifyTOD.put(obdTOD.COMMANDED_EGR, 0);
        notifyTOD.put(obdTOD.EGR_ERROR, 0);
        notifyTOD.put(obdTOD.COMMANDED_EVA_PURGE, 0);
        notifyTOD.put(obdTOD.FUEL_LEVEL_INPUT, 0);
        notifyTOD.put(obdTOD.NUMBER_WARMUPS, 0);
        notifyTOD.put(obdTOD.DISTANCE_TRAVELED, 0);
        notifyTOD.put(obdTOD.EV_VAPOR_PRESSURE, 0);
        notifyTOD.put(obdTOD.BAROMETRIC_PRESSURE, 0);
        notifyTOD.put(obdTOD.O2S1_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S2_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S3_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S4_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S5_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S6_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S7_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.O2S8_WR_LAMBDA_RATIO_CURRENT, 0);
        notifyTOD.put(obdTOD.CATALYST_TEMP_B1S1, 0);
        notifyTOD.put(obdTOD.CATALYST_TEMP_B2S1, 0);
        notifyTOD.put(obdTOD.CATALYST_TEMP_B1S2, 0);
        notifyTOD.put(obdTOD.CATALYST_TEMP_B2S2, 0);
        notifyTOD.put(obdTOD.PIDS_SUPPORTED_41_60, 0);
        notifyTOD.put(obdTOD.MONITOR_STATUS, 0);
        notifyTOD.put(obdTOD.CONTROL_MODULE_VOLTAGE, 0);
        notifyTOD.put(obdTOD.ABSOLUTE_LOAD_VALUE, 0);
        notifyTOD.put(obdTOD.FUEL_AIR_RATIO, 0);
        notifyTOD.put(obdTOD.RELATIVE_THROTTLE_POSITION, 0);
        notifyTOD.put(obdTOD.AMBIENT_AIR_PRESSURE, 0);
        notifyTOD.put(obdTOD.ABSOLUTE_THROTTLE_POSITION_B, 0);
        notifyTOD.put(obdTOD.ABSOLUTE_THROTTLE_POSITION_C, 0);
        notifyTOD.put(obdTOD.ACC_PEDAL_POSITION_D, 0);
        notifyTOD.put(obdTOD.ACC_PEDAL_POSITION_E, 0);
        notifyTOD.put(obdTOD.ACC_PEDAL_POSITION_F, 0);
        notifyTOD.put(obdTOD.COMMANDED_THROTTLE_ACTUATOR, 0);
        notifyTOD.put(obdTOD.TIME_MIL_ON, 0);
        notifyTOD.put(obdTOD.TIME_TROUBLE_CODES_CLEAR, 0);
        notifyTOD.put(obdTOD.MAXVAL_EQRATIO_OSVOLTAGE_OSCURRENT_IMPRESSURE, 0);
        notifyTOD.put(obdTOD.MAXVAL_AIRFLOW_RATE, 0);
        notifyTOD.put(obdTOD.FUEL_TYPE, 0);
        notifyTOD.put(obdTOD.ETHANOL_PERCENTAGE, 0);
        notifyTOD.put(obdTOD.ABSOLUTE_EVAP_PRESSURE, 0);
        notifyTOD.put(obdTOD.EVAP_PRESSURE, 0);
        notifyTOD.put(obdTOD.ST_SECONDARY_SENSOR_B1_B3, 0);
        notifyTOD.put(obdTOD.LT_SECONDARY_SENSOR_B1_B3, 0);
        notifyTOD.put(obdTOD.ST_SECONDARY_SENSOR_B2_B4, 0);
        notifyTOD.put(obdTOD.LT_SECONDARY_SENSOR_B2_B4, 0);
        notifyTOD.put(obdTOD.FUEL_RAIL_PRESSURE, 0);
        notifyTOD.put(obdTOD.RELATIVE_ACC_PEDAL_POSITION, 0);
        notifyTOD.put(obdTOD.HYBRID_BATTLE_REMAINING_LIFE, 0);
        notifyTOD.put(obdTOD.ENGINE_OIL_TEMPERATURE, 0);
        notifyTOD.put(obdTOD.FUEL_INJECTION_TIMING, 0);
        notifyTOD.put(obdTOD.ENGINE_FUEL_RATE, 0);
        notifyTOD.put(obdTOD.EMISSION_REQUIREMENTS, 0);
        notifyTOD.put(obdTOD.PIDS_SUPPORTED_61_80, 0);
        notifyTOD.put(obdTOD.DRIVER_DEMAND_PERCENT_TORQUE, 0);
        notifyTOD.put(obdTOD.ACTUAL_ENGINE_PERCENT_TORQUE, 0);
        notifyTOD.put(obdTOD.ENGINE_REFERENCE_TORQUE, 0);
        notifyTOD.put(obdTOD.ENGINE_PERCENT_TORQUE_DATA, 0);
        notifyTOD.put(obdTOD.AUX_IN_OUT_SUPPORTED, 0);

    }

    /**
     *
     * @return map with the values.
     */
    Map<TODint, Integer> getnotifyTOD()
    {
        return notifyTOD;
    }
}
