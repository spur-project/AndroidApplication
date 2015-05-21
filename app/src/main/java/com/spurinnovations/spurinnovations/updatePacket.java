package com.spurinnovations.spurinnovations;

/**
 * Created by Manuel on 2015-05-20.
 */
public class updatePacket extends Packet {
    /**
     * Constructor: it will create a packet to hold all the updates.
     */
    public updatePacket()
    {
        super();
        bytebuffer.put(ConstantDefinitions.UPDATE_PACKET);
        length = 3;
    }

    /**
     * adds the data to the packet
     * @param data update byte
     * @return true if added, false on overflow
     */
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
