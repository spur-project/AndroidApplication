package com.spurinnovations.spurinnovations;

import java.util.Arrays;

/**
 * Created by Manuel on 2015-05-15.
 */
public class ByteBufferSem {

    private static final byte END_SEQUENCE = (byte) 0x04;

    private byte[] mainBuffer;
    private int position;
    private int elementsNumber;
    private boolean isReady;
    private int packet_length;
    private int buffersize;


    ByteBufferSem(int max_capacity)
    {
        buffersize= max_capacity;
        mainBuffer = new byte[max_capacity];
        position = 0;
        elementsNumber = 0;
        isReady = false;
        packet_length = 0;
    }

    public void write(byte data)
    {
        if(data == END_SEQUENCE)
        {
            mainBuffer[position] = data;
            elementsNumber++;
            isReady = true;
            position = 0;
        }
        else
        {
            mainBuffer[position] = data;
            position++;
            elementsNumber++;
        }
    }

    public boolean isReady()
    {
        return isReady;
    }

    public int getElementsNumber()
    {
        return elementsNumber;
    }

    public byte[] getData()
    {
        byte[] currentData = Arrays.copyOf(mainBuffer, elementsNumber);
        return currentData;
    }
}
