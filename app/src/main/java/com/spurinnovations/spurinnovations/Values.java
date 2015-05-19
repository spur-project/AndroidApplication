package com.spurinnovations.spurinnovations;

import java.util.Arrays;

/**
 * Object to hold byte arrays (values)
 * @author Manuel
 */
public class Values {

    byte[] value;
    int length;

    /**
     * Constructor will create a value object out of a byte array and its length.
     * @param value data
     * @param length data size
     */
    Values(byte[] value, int length)
    {
        this.value = Arrays.copyOf(value, length);
        this.length = length;
    }

    /**
     *
     * @return data/value
     */
    public byte[] getValue()
    {
        return value;
    }

    /**
     *
     * @return length/size
     */
    public int getLength()
    {
        return length;
    }

}
