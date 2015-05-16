package com.spurinnovations.spurinnovations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-12.
 */
public class ByteToD {

    Map<TODint, Integer> ByteToD = new HashMap<TODint, Integer>();

    ByteToD()
    {

        ByteToD.put(NormalTOD.ANDROID_DEVICE_PRIVILEGES, 2);
        ByteToD.put(NormalTOD.DEVICE_CONFIGURATION, 2);
        ByteToD.put(NormalTOD.CONTROL_STATUS, 2);
        ByteToD.put(NormalTOD.CAMERA_STATUS, 2);
        ByteToD.put(NormalTOD.VEHICLE_STATUS, 2);
        ByteToD.put(NormalTOD.VEHICLE_SPEED, 2);
        ByteToD.put(NormalTOD.ACCELERATORPEDAL_ACTUALPERCENT, 2);
        ByteToD.put(NormalTOD.ACCELERATORPEDAL_MAXALLOWPERCENT, 2);
        ByteToD.put(NormalTOD.VEHICLE_ACCELERATION, 2);
        ByteToD.put(NormalTOD.VEHICLE_LONGITUDIONAL_ACC, 2);
        ByteToD.put(NormalTOD.VEHICLE_CORNERING_ACCELERATION, 2);
        ByteToD.put(NormalTOD.POSTED_SPEED_LIMIT, 1);
        ByteToD.put(NormalTOD.ELAPSED_TIME_SPEED_LIMIT, 2);
        ByteToD.put(NormalTOD.HEADWAY, 1);
        ByteToD.put(NormalTOD.BATTERY_VOLTAGE, 1);
        ByteToD.put(NormalTOD.LATITUDE,  4);
        ByteToD.put(NormalTOD.LONGITUDE, 4);
        ByteToD.put(NormalTOD.ALTITUDE, 2);
        ByteToD.put(NormalTOD.SOFTWARE_REVISION, 2);
        ByteToD.put(NormalTOD.ACCELERATOR_PEDAL_CONFIG, 2);
        ByteToD.put(NormalTOD.BRAKE_PEDAL_PERCENT, 2);
        ByteToD.put(NormalTOD.ODOMETER, 4);
        ByteToD.put(obdTOD.PIDS_SUPPORTED_1_20, 4);
        ByteToD.put(obdTOD.MONITOR_DTC, 4);
        ByteToD.put(obdTOD.FREEZE_DTC, 2);
        ByteToD.put(obdTOD.FUEL_SYSTEM_STATUS, 2);
        ByteToD.put(obdTOD.ENGINE_LOAD, 1);
        ByteToD.put(obdTOD.COOLANT_TEMPERATURE, 1);
        ByteToD.put(obdTOD.SHORT_TERM_FUEL_B1, 1);
        ByteToD.put(obdTOD.LONG_TERM_FUEL_B1, 1);
        ByteToD.put(obdTOD.SHORT_TERM_FUEL_B2, 1);
        ByteToD.put(obdTOD.LONG_TERM_FUEL_B2, 1);
        ByteToD.put(obdTOD.FUEL_PRESSURE, 1);
        ByteToD.put(obdTOD.MANIFOLD_PRESSURE, 1);
        ByteToD.put(obdTOD.ENGINE_RPM, 2);
        ByteToD.put(obdTOD.VEHICLE_SPEED, 1);
        ByteToD.put(obdTOD.TIMING_ADVANCE, 1);
        ByteToD.put(obdTOD.AIR_TEMPERATURE, 1);
        ByteToD.put(obdTOD.MAF_AIRFLOW, 2);
        ByteToD.put(obdTOD.THROTTLE_POSITION, 1);
        ByteToD.put(obdTOD.SECONDARY_AIR_STATUS, 1);
        ByteToD.put(obdTOD.OXYGEN_SENSORS, 1);
        ByteToD.put(obdTOD.B1_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B1_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B1_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B1_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B2_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B2_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B2_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.B2_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(obdTOD.OBD_STANDARD, 1);
        ByteToD.put(obdTOD.OXYGEN_SENSORS_SECOND, 1);
        ByteToD.put(obdTOD.AUX_INPUT_STATUS, 1);
        ByteToD.put(obdTOD.RUNTIME_ENGINE, 2);
        ByteToD.put(obdTOD.PIDS_SUPPORTED_21_40, 4);
        ByteToD.put(obdTOD.DISTANCE_MIL_ON, 2);
        ByteToD.put(obdTOD.FUEL_RAIL_PRESSURE_VACUUM, 2);
        ByteToD.put(obdTOD.FUEL_RAIL_PRESSURE_INJECTION, 2);
        ByteToD.put(obdTOD.O2S1_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S2_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S3_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S4_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S5_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S6_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S7_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.O2S8_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(obdTOD.COMMANDED_EGR, 1);
        ByteToD.put(obdTOD.EGR_ERROR, 1);
        ByteToD.put(obdTOD.COMMANDED_EVA_PURGE, 1);
        ByteToD.put(obdTOD.FUEL_LEVEL_INPUT, 1);
        ByteToD.put(obdTOD.NUMBER_WARMUPS, 1);
        ByteToD.put(obdTOD.DISTANCE_TRAVELED, 2);
        ByteToD.put(obdTOD.EV_VAPOR_PRESSURE, 2);
        ByteToD.put(obdTOD.BAROMETRIC_PRESSURE, 1);
        ByteToD.put(obdTOD.O2S1_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S2_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S3_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S4_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S5_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S6_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S7_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.O2S8_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(obdTOD.CATALYST_TEMP_B1S1, 2);
        ByteToD.put(obdTOD.CATALYST_TEMP_B2S1, 2);
        ByteToD.put(obdTOD.CATALYST_TEMP_B1S2, 2);
        ByteToD.put(obdTOD.CATALYST_TEMP_B2S2, 2);
        ByteToD.put(obdTOD.PIDS_SUPPORTED_41_60, 4);
        ByteToD.put(obdTOD.MONITOR_STATUS, 4);
        ByteToD.put(obdTOD.CONTROL_MODULE_VOLTAGE, 2);
        ByteToD.put(obdTOD.ABSOLUTE_LOAD_VALUE, 2);
        ByteToD.put(obdTOD.FUEL_AIR_RATIO, 2);
        ByteToD.put(obdTOD.RELATIVE_THROTTLE_POSITION, 1);
        ByteToD.put(obdTOD.AMBIENT_AIR_PRESSURE, 1);
        ByteToD.put(obdTOD.ABSOLUTE_THROTTLE_POSITION_B, 1);
        ByteToD.put(obdTOD.ABSOLUTE_THROTTLE_POSITION_C, 1);
        ByteToD.put(obdTOD.ACC_PEDAL_POSITION_D, 1);
        ByteToD.put(obdTOD.ACC_PEDAL_POSITION_E, 1);
        ByteToD.put(obdTOD.ACC_PEDAL_POSITION_F, 1);
        ByteToD.put(obdTOD.COMMANDED_THROTTLE_ACTUATOR, 1);
        ByteToD.put(obdTOD.TIME_MIL_ON, 2);
        ByteToD.put(obdTOD.TIME_TROUBLE_CODES_CLEAR, 2);
        ByteToD.put(obdTOD.MAXVAL_EQRATIO_OSVOLTAGE_OSCURRENT_IMPRESSURE, 4);
        ByteToD.put(obdTOD.MAXVAL_AIRFLOW_RATE, 4);
        ByteToD.put(obdTOD.FUEL_TYPE, 1);
        ByteToD.put(obdTOD.ETHANOL_PERCENTAGE, 1);
        ByteToD.put(obdTOD.ABSOLUTE_EVAP_PRESSURE, 2);
        ByteToD.put(obdTOD.EVAP_PRESSURE, 2);
        ByteToD.put(obdTOD.ST_SECONDARY_SENSOR_B1_B3, 2);
        ByteToD.put(obdTOD.LT_SECONDARY_SENSOR_B1_B3, 2);
        ByteToD.put(obdTOD.ST_SECONDARY_SENSOR_B2_B4, 2);
        ByteToD.put(obdTOD.LT_SECONDARY_SENSOR_B2_B4, 2);
        ByteToD.put(obdTOD.FUEL_RAIL_PRESSURE, 2);
        ByteToD.put(obdTOD.RELATIVE_ACC_PEDAL_POSITION, 1);
        ByteToD.put(obdTOD.HYBRID_BATTLE_REMAINING_LIFE, 1);
        ByteToD.put(obdTOD.ENGINE_OIL_TEMPERATURE, 1);
        ByteToD.put(obdTOD.FUEL_INJECTION_TIMING, 2);
        ByteToD.put(obdTOD.ENGINE_FUEL_RATE, 2);
        ByteToD.put(obdTOD.EMISSION_REQUIREMENTS, 1);
        ByteToD.put(obdTOD.PIDS_SUPPORTED_61_80, 4);
        ByteToD.put(obdTOD.DRIVER_DEMAND_PERCENT_TORQUE, 1);
        ByteToD.put(obdTOD.ACTUAL_ENGINE_PERCENT_TORQUE, 1);
        ByteToD.put(obdTOD.ENGINE_REFERENCE_TORQUE, 2);
        ByteToD.put(obdTOD.ENGINE_PERCENT_TORQUE_DATA, 5);
        ByteToD.put(obdTOD.AUX_IN_OUT_SUPPORTED, 2);

    }

    Map<TODint, Integer> getByteToD()
    {
        return ByteToD;
    }


}
