package com.spurinnovations.spurinnovations;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-15.
 */
public class ValidatePacket {

    private static final byte REQUEST_PACKET = (byte) 0x00;
    private static final byte BACKOFF_PACKET = (byte) 0x80;
    private static final byte UPDATE_PACKET = (byte) 0xff;
    private static final byte START_SEQUENCE = (byte) 0x02;
    private static final byte END_SEQUENCE = (byte) 0x04;
    private static final byte ESCAPE_SEQUENCE = (byte) 0x1B;
    private static final int BYTEMASK = 0xff;
    private static final byte EXTENDED_TOD = (byte) 0x7f;
    private static final byte EXTENDED_OBD = (byte) 0x81;

    private byte[] dataBuffer;
    private byte[] bytebuffer;
    private int packetlength;
    ByteToD valueList;
    Map<TODint, Integer> values;

    ValidatePacket()
    {
        valueList = new ByteToD();
        values = valueList.getByteToD();
    }

    public boolean validate(byte[] buffer, int length) {
        dataBuffer = new byte[length];
        int datasize = 0;
        int currentbyte_read = 0;
        int currentbyte_write = 0;


        if (buffer[currentbyte_read] == START_SEQUENCE) {

            packetlength = buffer[++currentbyte_read] & BYTEMASK;

            if (packetlength != length) {
                return false;
            }

            switch (buffer[++currentbyte_read]) {
                case REQUEST_PACKET:

                    break;

                case BACKOFF_PACKET:

                    break;

                case UPDATE_PACKET:

                    while (true) {

                        if (buffer[++currentbyte_read] == END_SEQUENCE && currentbyte_read == packetlength) {

                            bytebuffer = Arrays.copyOf(dataBuffer, currentbyte_write - 1);
                            break;
                        }

                        if(checkUnexpectedSequence(buffer[currentbyte_read]))
                        {
                            return false;
                        }

                        if (buffer[currentbyte_read] <= EXTENDED_TOD)
                        {

                            datasize = values.get(NormalTOD.valueOf(buffer[currentbyte_read] & BYTEMASK));
                            dataBuffer[currentbyte_write++] = buffer[currentbyte_read];

                            int i = 0;
                            while(i < datasize)
                            {
                                if(buffer[++currentbyte_read] == ESCAPE_SEQUENCE)
                                {
                                    dataBuffer[currentbyte_write++] = (byte) (buffer[++currentbyte_read] - 1);

                                    if(checkUnexpectedSequence(buffer[currentbyte_read]))
                                    {
                                        return false;
                                    }
                                    i++;
                                }
                                else
                                {
                                    dataBuffer[currentbyte_write++] = buffer[currentbyte_read];

                                    if(checkUnexpectedSequence(buffer[currentbyte_read]))
                                    {
                                        return false;
                                    }
                                    i++;
                                }
                            }

                        } else {
                            dataBuffer[currentbyte_write++] = buffer[currentbyte_read];

                            switch(buffer[currentbyte_read])
                            {
                                case EXTENDED_OBD:

                                    datasize = values.get(obdTOD.valueOf(buffer[++currentbyte_read] & BYTEMASK));
                                    dataBuffer[currentbyte_write++] = buffer[currentbyte_read];

                                    break;
                            }

                            int i = 0;

                            while(i < datasize)
                            {
                                if(buffer[++currentbyte_read] == ESCAPE_SEQUENCE)
                                {
                                    dataBuffer[currentbyte_write++] = (byte) (buffer[++currentbyte_read] - 1);

                                    if(checkUnexpectedSequence(buffer[currentbyte_read]))
                                    {
                                        return false;
                                    }
                                    i++;
                                }
                                else
                                {
                                    dataBuffer[currentbyte_write++] = buffer[currentbyte_read];

                                    if(checkUnexpectedSequence(buffer[currentbyte_read]))
                                    {
                                        return false;
                                    }
                                    i++;
                                }
                            }
                        }
                    }

                    break;

            }


        } else {
            return false;
        }

        return true;
    }

    private boolean checkUnexpectedSequence(byte dataByte)
    {
        if(dataByte == START_SEQUENCE || dataByte == END_SEQUENCE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public byte[] getValidatedPacket()
    {
        return bytebuffer;
    }
}
