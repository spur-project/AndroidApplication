package com.spurinnovations.spurinnovations;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * A class that will build a packet object according to the protocol
 * @author Manuel
 */
public class Packet {

    byte[] packet;
    byte[] packetReady;
    int length;
    ByteBuffer bytebuffer;

    /**
     * Constructor, will create the packet with the starting sequence.
     */
    Packet()
    {
        bytebuffer = ByteBuffer.allocate(256);
        bytebuffer.put(ConstantDefinitions.START_SEQUENCE);
        bytebuffer.put((byte) 0);
        length = 2;

    }

    /**
     * Will add the data into the packet
     * @param data byte array of data
     * @param size sie of data
     * @return true if data was added, false if packet is full
     */
    public boolean addData(byte[] data, int size)
    {
        if(length + size > 255)
        {
            return false;
        }

        bytebuffer.put(data, length, size);
        length += size;

        return true;
    }

    /**
     * This function will wrap the packet and will send it trough the selected outputstream
     * @param writeOut outputstream to send the data trough
     */
    public void sendPacket(OutputStream writeOut)
    {
        bytebuffer.put(ConstantDefinitions.END_SEQUENCE);
        packetReady = new byte[length + 1];
        bytebuffer.clear();
        bytebuffer.get(packetReady, 0, length + 1);
        packetReady[1] = (byte) length;

        StringBuffer sb = new StringBuffer();
        for( byte b : packetReady )
            sb.append(Integer.toHexString( b ));
        Log.d(ConstantDefinitions.TAG, sb.toString());
        Log.d(ConstantDefinitions.TAG, bytebuffer.toString());
        Log.d(ConstantDefinitions.TAG, Integer.toString(length + 1));

        try {
            writeOut.write(packetReady);
            writeOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
