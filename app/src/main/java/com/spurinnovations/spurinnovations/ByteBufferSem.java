package com.spurinnovations.spurinnovations;

import android.util.Log;

import java.util.Arrays;

/**
 * Byte Buffer object, used to write data into a byte array.
 * @author Manuel
 */
public class ByteBufferSem {

    private byte[] mainBuffer;
    private int position;
    private int elementsNumber;
    private boolean isReady;
    private int packet_length;
    private int buffersize;


    /**
     * Constructor will instantiate the byte array and all the variables.
     * @param max_capacity size of the buffer
     */
    ByteBufferSem(int max_capacity)
    {
        buffersize= max_capacity;
        mainBuffer = new byte[max_capacity];
        position = 0;
        elementsNumber = 0;
        isReady = false;
        packet_length = 0;
    }

    /**
     * It will add the passed byte into the byte array, if the number exceeds the maximum
     * capacity the byte will be ignored.
     * @param data byte of data
     */
    public void write(byte data)
    {
        if(elementsNumber < buffersize)
        {
            if (data == ConstantDefinitions.END_SEQUENCE) {
                mainBuffer[position] = data;
                elementsNumber++;
                isReady = true;
                position = 0;
            } else {
                mainBuffer[position] = data;
                position++;
                elementsNumber++;
            }
        }
    }

    /**
     * It will return a boolean for whether or not a packet is ready
     * @return if packet is ready
     */
    public boolean isReady()
    {
        boolean temp = isReady;
        isReady = false;
        return temp;
    }

    /**
     *
     * @return number of elements in the buffer
     */
    public int getElementsNumber()
    {
        int temp = elementsNumber;
        elementsNumber = 0;
        return temp;
    }

    /**
     * This function should be called after the packet isReady so it will return
     * a complete packet
     * @return complete packet
     */
    public byte[] getData()
    {
        byte[] currentData = Arrays.copyOf(mainBuffer, elementsNumber);
        StringBuffer sb = new StringBuffer();
        for( byte b : currentData )
            sb.append(Integer.toHexString( b ));
        Log.d(ConstantDefinitions.TAG, sb.toString());
        Log.d(ConstantDefinitions.TAG, Integer.toString(elementsNumber));
        return currentData;
    }
}
