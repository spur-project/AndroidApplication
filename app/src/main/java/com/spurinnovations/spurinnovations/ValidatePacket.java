package com.spurinnovations.spurinnovations;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-15.
 */
public class ValidatePacket {

    private byte[] dataBuffer;
    private byte[] bytebuffer;
    private int packetlength;

    ValidatePacket()
    {

    }

    public boolean validate(byte[] buffer, int length) {
        dataBuffer = new byte[length];
        byte[] noContent = {ConstantDefinitions.END_SEQUENCE};
        int currentbyte_read = 0;
        int currentbyte_write = 0;


        if (buffer[currentbyte_read] == ConstantDefinitions.START_SEQUENCE) {

            packetlength = buffer[++currentbyte_read] & ConstantDefinitions.BYTEMASK;

            if (packetlength != length) {
                return false;
            }

            switch (buffer[++currentbyte_read]) {
                case ConstantDefinitions.REQUEST_PACKET:

                    break;

                case ConstantDefinitions.BACKOFF_PACKET:

                    break;

                case ConstantDefinitions.UPDATE_PACKET:

                    while (true) {

                        if (buffer[++currentbyte_read] == ConstantDefinitions.END_SEQUENCE && currentbyte_read == packetlength) {

                            if(packetlength > 4) {
                                bytebuffer = Arrays.copyOf(dataBuffer, currentbyte_write - 1);
                            }
                            else
                            {
                                bytebuffer = Arrays.copyOf(noContent, 1);
                            }

                            break;
                        }

                        if(checkUnexpectedSequence(buffer[currentbyte_read]))
                        {
                            return false;
                        }
                        else
                        {
                            if(buffer[currentbyte_read] == ConstantDefinitions.ESCAPE_SEQUENCE)
                            {
                                dataBuffer[currentbyte_write++] = (byte) (buffer[++currentbyte_read] - 1);
                            }
                            else
                            {
                                dataBuffer[currentbyte_write++] = buffer[currentbyte_read];
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
        if(dataByte == ConstantDefinitions.START_SEQUENCE || dataByte == ConstantDefinitions.END_SEQUENCE)
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
