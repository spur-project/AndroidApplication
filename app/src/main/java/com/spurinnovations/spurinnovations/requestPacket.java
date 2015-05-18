package com.spurinnovations.spurinnovations;

/**
 * Created by Manuel on 2015-05-17.
 */
public class requestPacket extends Packet
{

    public requestPacket()
    {
        super();
    }


    public boolean addData(byte data)
    {
        if(length + 1 > 256)
        {
            return false;
        }

        bytebuffer.put(data);
        length += 1;

        return true;
    }
}
