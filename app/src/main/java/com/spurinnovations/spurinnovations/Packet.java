package com.spurinnovations.spurinnovations;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by Manuel on 2015-05-17.
 */
public class Packet {

    byte[] packet;
    byte[] packetReady;
    int length;
    ByteBuffer bytebuffer;

    Packet()
    {
        packet = new byte[256];
        bytebuffer.wrap(packet);
        bytebuffer.put(ConstantDefinitions.START_SEQUENCE);
        bytebuffer.put((byte) 0);
        length = 2;

    }

    public boolean addData(byte[] data, int size)
    {
        if(length + size > 256)
        {
            return false;
        }

        bytebuffer.put(data, length, size);
        length += size;

        return true;
    }

    public void sendPacket(OutputStream writeOut)
    {
        packetReady = new byte[length];
        bytebuffer.get(packetReady, 0, length-1);
        packetReady[1] = (byte) length;

        try {
            writeOut.write(packetReady);
            writeOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
