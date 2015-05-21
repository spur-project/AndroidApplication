package com.spurinnovations.spurinnovations;

import android.util.Log;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to parse a valid packet just received.
 * It will parse each TOD and place it into the maps.
 * @author Manuel
 */
public class ParsePacket {

    //map for actual data TOD/ byte pair
    private Map<TODint, Values> dataMap;

    //map for parsed data TOD/ string pair
    private Map<TODint, String> stringMap;

    //map to hold the sign values for each TOD
    Map<TODint, Integer> signs;

    //map to hold the notifications for each TOD
    Map<TODint, Integer> notification;

    //map to hold the number of bytes for each TOD
    Map<TODint, Integer> values;


    /**
     * Constructor it will instantiate all the maps.
     * @param stringMap map to store parsed data
     */
    ParsePacket(Map<TODint, String> stringMap)
    {
        dataMap = new HashMap<TODint, Values>();
        this.stringMap = stringMap;

        ByteToD valueList = new ByteToD();
        values = valueList.getByteToD();

        signTOD signList = new signTOD();
        signs = signList.getsignTOD();

        notifyTOD notifyList = new notifyTOD();
        notification = notifyList.getnotifyTOD();
    }

    /**
     * It will read the data from the packet and will store it on the right TOD for each
     * map
     * @param buffer valid packet
     */
    public void parseData(byte[] buffer)
    {
        int bufferSize = buffer.length;

        StringBuffer sb = new StringBuffer();
        for( byte b : buffer )
            sb.append(Integer.toHexString( b ));
        Log.d(ConstantDefinitions.TAG, sb.toString());
        Log.d(ConstantDefinitions.TAG, Integer.toString(bufferSize));


        int cursor = -1;
        int datasize = 0;
        int ToD = 0;
        byte[] data = new byte[256];

        while(cursor < bufferSize - 1) {

            if (buffer[++cursor] <= ConstantDefinitions.EXTENDED_TOD)
            {
                int debugger = buffer[cursor] &  ConstantDefinitions.BYTEMASK;
                Log.d(  ConstantDefinitions.TAG, "FOLLOWING TYPE RECEIVED");
                Log.d( ConstantDefinitions.TAG, Integer.toString(debugger) );

                try {
                    datasize = values.get(NormalTOD.valueOf(buffer[cursor] & ConstantDefinitions.BYTEMASK));
                }catch(NullPointerException e)
                {
                    Log.d(ConstantDefinitions.TAG, "Unknown type");

                }

                Log.d(ConstantDefinitions.TAG, Integer.toString(datasize));


                ToD = buffer[cursor] & ConstantDefinitions.BYTEMASK;

                for(int i = 0; i < datasize; i++)
                {
                    data[i] = buffer[++cursor];
                }

                Values value = new Values(data, datasize);
                dataMap.put(NormalTOD.valueOf(ToD), value);
                putStringMap(NormalTOD.valueOf(ToD), value.getValue(), datasize);
                notification.put(NormalTOD.valueOf(ToD), 1);



            }
            else
            {
                switch (buffer[cursor])
                {
                    case ConstantDefinitions.EXTENDED_OBD:

                        datasize = values.get(obdTOD.valueOf(buffer[++cursor] & ConstantDefinitions.BYTEMASK));
                        ToD = buffer[cursor] & ConstantDefinitions.BYTEMASK;

                        for(int i = 0; i < datasize; i++)
                        {
                            data[i] = buffer[++cursor];
                        }

                        Values value = new Values(data, datasize);
                        dataMap.put(obdTOD.valueOf(ToD), value);
                        putStringMap(obdTOD.valueOf(ToD), value.getValue(), datasize);
                        notification.put(obdTOD.valueOf(ToD), 1);

                        break;

                    default:

                        Log.d("DEBUG", "UNKNOWN DATA");

                        break;
                }


            }
        }
    }

    /**
     * This function will parse the bytes received into a uint_8, uint_16, uint_32,
     * int_16, int_32 and will store them as strings into the map.
     * @param TOD type of data received
     * @param data actual data
     * @param datasize size of data
     */
    private void putStringMap(TODint TOD, byte[] data, int datasize)
    {
        ByteBuffer bytebuffer = ByteBuffer.wrap(data);
        int value = 0;
        short value_short = 0;
        long value_long = 0;

        try {
            if (signs.get(TOD) == 0) {
                switch (datasize) {
                    case 1:

                        value = bytebuffer.get(0) & ConstantDefinitions.BYTEMASK;
                        stringMap.put(TOD, Integer.toString(value));

                        break;
                    case 2:

                        value = bytebuffer.getShort(0) & ConstantDefinitions.BYTEMASK;
                        stringMap.put(TOD, Integer.toString(value));
                        break;

                    case 4:

                        value_long = bytebuffer.getInt(0) & 0xfffffffL;
                        stringMap.put(TOD, Long.toString(value_long));

                        break;

                    default:

                        break;
                }
            } else if (signs.get(TOD) == 1) {
                switch (datasize) {
                /*case 1:

                    value = bytebuffer.get(0) & ConstantDefinitions.BYTEMASK;
                    stringMap.put(TOD, Integer.toString(value));

                    break;*/
                    case 2:

                        value_short = bytebuffer.getShort(0);
                        stringMap.put(TOD, Short.toString(value_short));
                        break;

                    case 4:

                        value = bytebuffer.getInt(0);
                        stringMap.put(TOD, Integer.toString(value));

                        break;

                    default:

                        break;
                }
            }
        }
        catch(NullPointerException e)
        {
        }
    }

    /**
     * This function looks for all updated values for the TODs specified on MainView
     * It will return a list of all TODs that have a notification value of 1.
     * @return list of all updated TODs
     */
    public List<TODint> getMainViewUpdates()
    {
        TODint[] mainViewTOD = {NormalTOD.POSTED_SPEED_LIMIT,
                                NormalTOD.VEHICLE_SPEED,
                                NormalTOD.ELAPSED_TIME_SPEED_LIMIT,
                                NormalTOD.VEHICLE_ACCELERATION,
                                NormalTOD.VEHICLE_CORNERING_ACCELERATION,
                                NormalTOD.CURRENT_LEEWAY};

        List<TODint> updateList = new ArrayList<TODint>();


        for(TODint TOD : mainViewTOD)
        {
            if(notification.get(TOD) == 1)
            {
                updateList.add(TOD);
                notification.put(TOD, 0);
            }
        }

        Log.d(ConstantDefinitions.TAG, updateList.toString());
        return updateList;
    }

    /**
     * This function looks for all updated values for the TODs specified on ProfileView
     * It will return a list of all TODs that have a notification value of 1.
     * @return list of all updated TODs
     */
    public List<TODint> getProfileUpdates()
    {
        TODint[] profileViewTOD = { NormalTOD.ANDROID_DEVICE_PRIVILEGES,
                                    NormalTOD.DEVICE_CONFIGURATION,
                                    NormalTOD.CONTROL_STATUS,
                                    NormalTOD.CAMERA_STATUS,
                                    NormalTOD.VEHICLE_STATUS,
                                    NormalTOD.ACCELERATORPEDAL_ACTUALPERCENT,
                                    NormalTOD.ACCELERATORPEDAL_MAXALLOWPERCENT,
                                    NormalTOD.VEHICLE_LONGITUDIONAL_ACC,
                                    NormalTOD.HEADWAY,
                                    NormalTOD.BATTERY_VOLTAGE,
                                    NormalTOD.LATITUDE,
                                    NormalTOD.LONGITUDE,
                                    NormalTOD.ALTITUDE,
                                    NormalTOD.SOFTWARE_REVISION,
                                    NormalTOD.ACCELERATOR_PEDAL_CONFIG,
                                    NormalTOD.BRAKE_PEDAL_PERCENT,
                                    NormalTOD.ODOMETER};

        List<TODint> updateList = new ArrayList<TODint>();

        for(TODint TOD : profileViewTOD)
        {
            if(notification.get(TOD) == 1)
            {
                updateList.add(TOD);
                notification.put(TOD, 0);
            }
        }

        return updateList;
    }

    /**
     * This function looks for all updated values for the TODs specified on OBD
     * It will return a list of all TODs that have a notification value of 1.
     * @return list of all updated TODs
     */
    public List<TODint> getOBDUpdates()
    {
        TODint[] obdViewTOD = { obdTOD.PIDS_SUPPORTED_1_20,
                obdTOD.MONITOR_DTC,
                obdTOD.FREEZE_DTC,
                obdTOD.FUEL_SYSTEM_STATUS,
                obdTOD.ENGINE_LOAD,
                obdTOD.COOLANT_TEMPERATURE,
                obdTOD.SHORT_TERM_FUEL_B1,
                obdTOD.LONG_TERM_FUEL_B1,
                obdTOD.SHORT_TERM_FUEL_B2,
                obdTOD.LONG_TERM_FUEL_B2,
                obdTOD.FUEL_PRESSURE,
                obdTOD.MANIFOLD_PRESSURE,
                obdTOD.ENGINE_RPM,
                obdTOD.VEHICLE_SPEED,
                obdTOD.TIMING_ADVANCE,
                obdTOD.AIR_TEMPERATURE,
                obdTOD.MAF_AIRFLOW,
                obdTOD.THROTTLE_POSITION,
                obdTOD.SECONDARY_AIR_STATUS,
                obdTOD.OXYGEN_SENSORS,
                obdTOD.B1_S1_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B1_S2_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B1_S3_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B1_S4_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B2_S1_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B2_S2_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B2_S3_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.B2_S4_OXYGEN_SENSOR_VOLTAGE_FUEL,
                obdTOD.OBD_STANDARD,
                obdTOD.OXYGEN_SENSORS_SECOND,
                obdTOD.AUX_INPUT_STATUS,
                obdTOD.RUNTIME_ENGINE,
                obdTOD.PIDS_SUPPORTED_21_40,
                obdTOD.DISTANCE_MIL_ON,
                obdTOD.FUEL_RAIL_PRESSURE_VACUUM,
                obdTOD.FUEL_RAIL_PRESSURE_INJECTION,
                obdTOD.O2S1_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S2_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S3_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S4_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S5_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S6_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S7_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.O2S8_WR_LAMBDA_RATIO_VOLTAGE,
                obdTOD.COMMANDED_EGR,
                obdTOD.EGR_ERROR,
                obdTOD.COMMANDED_EVA_PURGE,
                obdTOD.FUEL_LEVEL_INPUT,
                obdTOD.NUMBER_WARMUPS,
                obdTOD.DISTANCE_TRAVELED,
                obdTOD.EV_VAPOR_PRESSURE,
                obdTOD.BAROMETRIC_PRESSURE,
                obdTOD.O2S1_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S2_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S3_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S4_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S5_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S6_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S7_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.O2S8_WR_LAMBDA_RATIO_CURRENT,
                obdTOD.CATALYST_TEMP_B1S1,
                obdTOD.CATALYST_TEMP_B2S1,
                obdTOD.CATALYST_TEMP_B1S2,
                obdTOD.CATALYST_TEMP_B2S2,
                obdTOD.PIDS_SUPPORTED_41_60,
                obdTOD.MONITOR_STATUS,
                obdTOD.CONTROL_MODULE_VOLTAGE,
                obdTOD.ABSOLUTE_LOAD_VALUE,
                obdTOD.FUEL_AIR_RATIO,
                obdTOD.RELATIVE_THROTTLE_POSITION,
                obdTOD.AMBIENT_AIR_PRESSURE,
                obdTOD.ABSOLUTE_THROTTLE_POSITION_B,
                obdTOD.ABSOLUTE_THROTTLE_POSITION_C,
                obdTOD.ACC_PEDAL_POSITION_D,
                obdTOD.ACC_PEDAL_POSITION_E,
                obdTOD.ACC_PEDAL_POSITION_F,
                obdTOD.COMMANDED_THROTTLE_ACTUATOR,
                obdTOD.TIME_MIL_ON,
                obdTOD.TIME_TROUBLE_CODES_CLEAR,
                obdTOD.MAXVAL_EQRATIO_OSVOLTAGE_OSCURRENT_IMPRESSURE,
                obdTOD.MAXVAL_AIRFLOW_RATE,
                obdTOD.FUEL_TYPE,
                obdTOD.ETHANOL_PERCENTAGE,
                obdTOD.ABSOLUTE_EVAP_PRESSURE,
                obdTOD.EVAP_PRESSURE,
                obdTOD.ST_SECONDARY_SENSOR_B1_B3,
                obdTOD.LT_SECONDARY_SENSOR_B1_B3,
                obdTOD.ST_SECONDARY_SENSOR_B2_B4,
                obdTOD.LT_SECONDARY_SENSOR_B2_B4,
                obdTOD.FUEL_RAIL_PRESSURE,
                obdTOD.RELATIVE_ACC_PEDAL_POSITION,
                obdTOD.HYBRID_BATTLE_REMAINING_LIFE,
                obdTOD.ENGINE_OIL_TEMPERATURE,
                obdTOD.FUEL_INJECTION_TIMING,
                obdTOD.ENGINE_FUEL_RATE,
                obdTOD.EMISSION_REQUIREMENTS,
                obdTOD.PIDS_SUPPORTED_61_80,
                obdTOD.DRIVER_DEMAND_PERCENT_TORQUE,
                obdTOD.ACTUAL_ENGINE_PERCENT_TORQUE,
                obdTOD.ENGINE_REFERENCE_TORQUE,
                obdTOD.ENGINE_PERCENT_TORQUE_DATA,
                obdTOD.AUX_IN_OUT_SUPPORTED};

        List<TODint> updateList = new ArrayList<TODint>();

        for(TODint TOD : obdViewTOD)
        {
            if(notification.get(TOD) == 1)
            {
                updateList.add(TOD);
                notification.put(TOD, 0);
            }
        }



        return updateList;
    }

    public List<TODint> getSettingsUpdates()
    {
        TODint[] profileViewTOD = { NormalTOD.SPEED_LEEWAY_PROFILE,
                                    NormalTOD.CUSTOM_SPEED_LEEWAY_BREAKPOINTS,
                                    NormalTOD.CUSTOM_SPEED_LEEWAY_VALUES};

        List<TODint> updateList = new ArrayList<TODint>();

        for(TODint TOD : profileViewTOD)
        {
            if(notification.get(TOD) == 1)
            {
                updateList.add(TOD);
                notification.put(TOD, 0);
            }
        }

        return updateList;
    }

    public Map<TODint, Values> getDataMap()
    {
        return dataMap;
    }


}
