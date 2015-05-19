package com.spurinnovations.spurinnovations;

import android.util.Log;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 * This class will validate a received packet and will either pass it if valid or discard it if
 * invalid.
 * @author Manuel
 */
public class ValidatePacket {

    private byte[] dataBuffer;
    private byte[] bytebuffer;
    private int packetlength;

    /**
     * Constructor
     */
    ValidatePacket()
    {

    }

    /**
     * Will validate the packet cheching that it counts with a start and ending sequence, a type of
     * packet and that the length send matches the length received. It will then get rid of all the
     * flags and will pass only the received data into a new packet for parsing
     * @param buffer packet data
     * @param length packet length
     * @return true if valid, false if invalid
     */
    public boolean validate(byte[] buffer, int length) {
        dataBuffer = new byte[length];
        byte[] noContent = {ConstantDefinitions.END_SEQUENCE};
        int currentbyte_read = 0;
        int currentbyte_write = 0;


        if (buffer[currentbyte_read] == ConstantDefinitions.START_SEQUENCE) {

            packetlength = buffer[++currentbyte_read] & ConstantDefinitions.BYTEMASK;

            Log.d(ConstantDefinitions.TAG, Integer.toString(packetlength));
            Log.d(ConstantDefinitions.TAG, Integer.toString(length));

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

                        if (buffer[++currentbyte_read] == ConstantDefinitions.END_SEQUENCE && currentbyte_read + 1 == packetlength) {

                            if(packetlength > 4) {
                                bytebuffer = Arrays.copyOf(dataBuffer, currentbyte_write);
                            }
                            else
                            {
                                bytebuffer = Arrays.copyOf(noContent, 1);
                            }

                            break;
                        }

                        if(checkUnexpectedSequence(buffer[currentbyte_read]))
                        {
                            Log.d(ConstantDefinitions.TAG, "FALSE");
                            Log.d(ConstantDefinitions.TAG, Integer.toString(currentbyte_read));
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

    /**
     * It will check that the packed does not contain any bytes resembling the sequences since
     * an escape character should be used before sending them.
     * @param dataByte byte of data
     * @return true if any sequence found, false otherwise
     */
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

    /**
     *
     * @return validated packet
     */
    public byte[] getValidatedPacket()
    {
        return bytebuffer;
    }
}
