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

        ByteToD.put(NormalTOD.SPEED, 1);
        ByteToD.put(NormalTOD.ACCELERATION, 1);
        ByteToD.put(NormalTOD.BREAKING, 1);
        ByteToD.put(NormalTOD.CORNERING, 1);
        ByteToD.put(NormalTOD.SPEED_LIMIT, 1);
        ByteToD.put(NormalTOD.IMPACT_TIME, 1);
        ByteToD.put(NormalTOD.IMPACT_OBJECT, 1);
        ByteToD.put(ExtendedTOD.PIDS_SUPPORTED_1_20, 4);
        ByteToD.put(ExtendedTOD.MONITOR_DTC, 4);
        ByteToD.put(ExtendedTOD.FREEZE_DTC, 2);
        ByteToD.put(ExtendedTOD.FUEL_SYSTEM_STATUS, 2);
        ByteToD.put(ExtendedTOD.ENGINE_LOAD, 1);
        ByteToD.put(ExtendedTOD.COOLANT_TEMPERATURE, 1);
        ByteToD.put(ExtendedTOD.SHORT_TERM_FUEL_B1, 1);
        ByteToD.put(ExtendedTOD.LONG_TERM_FUEL_B1, 1);
        ByteToD.put(ExtendedTOD.SHORT_TERM_FUEL_B2, 1);
        ByteToD.put(ExtendedTOD.LONG_TERM_FUEL_B2, 1);
        ByteToD.put(ExtendedTOD.FUEL_PRESSURE, 1);
        ByteToD.put(ExtendedTOD.MANIFOLD_PRESSURE, 1);
        ByteToD.put(ExtendedTOD.ENGINE_RPM, 2);
        ByteToD.put(ExtendedTOD.VEHICLE_SPEED, 1);
        ByteToD.put(ExtendedTOD.TIMING_ADVANCE, 1);
        ByteToD.put(ExtendedTOD.AIR_TEMPERATURE, 1);
        ByteToD.put(ExtendedTOD.MAF_AIRFLOW, 2);
        ByteToD.put(ExtendedTOD.THROTTLE_POSITION, 1);
        ByteToD.put(ExtendedTOD.SECONDARY_AIR_STATUS, 1);
        ByteToD.put(ExtendedTOD.OXYGEN_SENSORS, 1);
        ByteToD.put(ExtendedTOD.B1_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B1_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B1_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B1_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B2_S1_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B2_S2_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B2_S3_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.B2_S4_OXYGEN_SENSOR_VOLTAGE_FUEL, 2);
        ByteToD.put(ExtendedTOD.OBD_STANDARD, 1);
        ByteToD.put(ExtendedTOD.OXYGEN_SENSORS_SECOND, 1);
        ByteToD.put(ExtendedTOD.AUX_INPUT_STATUS, 1);
        ByteToD.put(ExtendedTOD.RUNTIME_ENGINE, 2);
        ByteToD.put(ExtendedTOD.PIDS_SUPPORTED_21_40, 4);
        ByteToD.put(ExtendedTOD.DISTANCE_MIL_ON, 2);
        ByteToD.put(ExtendedTOD.FUEL_RAIL_PRESSURE_VACUUM, 2);
        ByteToD.put(ExtendedTOD.FUEL_RAIL_PRESSURE_INJECTION, 2);
        ByteToD.put(ExtendedTOD.O2S1_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S2_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S3_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S4_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S5_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S6_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S7_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.O2S8_WR_LAMBDA_RATIO_VOLTAGE, 4);
        ByteToD.put(ExtendedTOD.COMMANDED_EGR, 1);
        ByteToD.put(ExtendedTOD.EGR_ERROR, 1);
        ByteToD.put(ExtendedTOD.COMMANDED_EVA_PURGE, 1);
        ByteToD.put(ExtendedTOD.FUEL_LEVEL_INPUT, 1);
        ByteToD.put(ExtendedTOD.NUMBER_WARMUPS, 1);
        ByteToD.put(ExtendedTOD.DISTANCE_TRAVELED, 2);
        ByteToD.put(ExtendedTOD.EV_VAPOR_PRESSURE, 2);
        ByteToD.put(ExtendedTOD.BAROMETRIC_PRESSURE, 1);
        ByteToD.put(ExtendedTOD.O2S1_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S2_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S3_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S4_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S5_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S6_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S7_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.O2S8_WR_LAMBDA_RATIO_CURRENT, 4);
        ByteToD.put(ExtendedTOD.CATALYST_TEMP_B1S1, 2);
        ByteToD.put(ExtendedTOD.CATALYST_TEMP_B2S1, 2);
        ByteToD.put(ExtendedTOD.CATALYST_TEMP_B1S2, 2);
        ByteToD.put(ExtendedTOD.CATALYST_TEMP_B2S2, 2);
        ByteToD.put(ExtendedTOD.PIDS_SUPPORTED_41_60, 4);
        ByteToD.put(ExtendedTOD.MONITOR_STATUS, 4);
        ByteToD.put(ExtendedTOD.CONTROL_MODULE_VOLTAGE, 2);
        ByteToD.put(ExtendedTOD.ABSOLUTE_LOAD_VALUE, 2);
        ByteToD.put(ExtendedTOD.FUEL_AIR_RATIO, 2);
        ByteToD.put(ExtendedTOD.RELATIVE_THROTTLE_POSITION, 1);
        ByteToD.put(ExtendedTOD.AMBIENT_AIR_PRESSURE, 1);
        ByteToD.put(ExtendedTOD.ABSOLUTE_THROTTLE_POSITION_B, 1);
        ByteToD.put(ExtendedTOD.ABSOLUTE_THROTTLE_POSITION_C, 1);
        ByteToD.put(ExtendedTOD.ACC_PEDAL_POSITION_D, 1);
        ByteToD.put(ExtendedTOD.ACC_PEDAL_POSITION_E, 1);
        ByteToD.put(ExtendedTOD.ACC_PEDAL_POSITION_F, 1);
        ByteToD.put(ExtendedTOD.COMMANDED_THROTTLE_ACTUATOR, 1);
        ByteToD.put(ExtendedTOD.TIME_MIL_ON, 2);
        ByteToD.put(ExtendedTOD.TIME_TROUBLE_CODES_CLEAR, 2);
        ByteToD.put(ExtendedTOD.MAXVAL_EQRATIO_OSVOLTAGE_OSCURRENT_IMPRESSURE, 4);
        ByteToD.put(ExtendedTOD.MAXVAL_AIRFLOW_RATE, 4);
        ByteToD.put(ExtendedTOD.FUEL_TYPE, 1);
        ByteToD.put(ExtendedTOD.ETHANOL_PERCENTAGE, 1);
        ByteToD.put(ExtendedTOD.ABSOLUTE_EVAP_PRESSURE, 2);
        ByteToD.put(ExtendedTOD.EVAP_PRESSURE, 2);
        ByteToD.put(ExtendedTOD.ST_SECONDARY_SENSOR_B1_B3, 2);
        ByteToD.put(ExtendedTOD.LT_SECONDARY_SENSOR_B1_B3, 2);
        ByteToD.put(ExtendedTOD.ST_SECONDARY_SENSOR_B2_B4, 2);
        ByteToD.put(ExtendedTOD.LT_SECONDARY_SENSOR_B2_B4, 2);
        ByteToD.put(ExtendedTOD.FUEL_RAIL_PRESSURE, 2);
        ByteToD.put(ExtendedTOD.RELATIVE_ACC_PEDAL_POSITION, 1);
        ByteToD.put(ExtendedTOD.HYBRID_BATTLE_REMAINING_LIFE, 1);
        ByteToD.put(ExtendedTOD.ENGINE_OIL_TEMPERATURE, 1);
        ByteToD.put(ExtendedTOD.FUEL_INJECTION_TIMING, 2);
        ByteToD.put(ExtendedTOD.ENGINE_FUEL_RATE, 2);
        ByteToD.put(ExtendedTOD.EMISSION_REQUIREMENTS, 1);
        ByteToD.put(ExtendedTOD.PIDS_SUPPORTED_61_80, 4);
        ByteToD.put(ExtendedTOD.DRIVER_DEMAND_PERCENT_TORQUE, 1);
        ByteToD.put(ExtendedTOD.ACTUAL_ENGINE_PERCENT_TORQUE, 1);
        ByteToD.put(ExtendedTOD.ENGINE_REFERENCE_TORQUE, 2);
        ByteToD.put(ExtendedTOD.ENGINE_PERCENT_TORQUE_DATA, 5);
        ByteToD.put(ExtendedTOD.AUX_IN_OUT_SUPPORTED, 2);

    }

    Map<TODint, Integer> getByteToD()
    {
        return ByteToD;
    }


}
