package com.spurinnovations.spurinnovations;

/**
 * Created by Manuel on 2015-05-17.
 */
public class ConstantDefinitions {

    public static final int REQUEST_CONNECT_DEVICE = 1;
    public static final int REQUEST_ENABLE_BT = 2;
    public static final String TAG = "DEBUG";

    public static final int STOP_PEDESTRIAN = 101;
    public static final int STOP_CAR = 102;
    public static final int STOP_SPEEDING = 103;

    public static final byte START_SEQUENCE = (byte) 0x02;
    public static final byte END_SEQUENCE = (byte) 0x04;
    public static final byte EXTENDED_TOD = (byte) 0x7f;
    public static final byte EXTENDED_OBD = (byte) 0x81;
    public static final int BYTEMASK = 0xff;
    public static final byte REQUEST_PACKET = (byte) 0x00;
    public static final byte BACKOFF_PACKET = (byte) 0x80;
    public static final byte UPDATE_PACKET = (byte) 0xff;
    public static final byte ESCAPE_SEQUENCE = (byte) 0x1B;
    public static final byte BACKOFF_PACKET_SIZE = (byte) 5;
}
