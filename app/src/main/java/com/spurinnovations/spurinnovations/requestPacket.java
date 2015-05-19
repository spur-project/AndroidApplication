package com.spurinnovations.spurinnovations;

/**
 * Class that creates a request packet to send over the network
 * @author Manuel
 */
public class requestPacket extends Packet
{

    /**
     * Constructor: it will create a packet to hold all the requests.
     */
    public requestPacket()
    {
        super();
        bytebuffer.put(ConstantDefinitions.REQUEST_PACKET);
        length = 3;
    }

    /**
     * adds the data to the packet
     * @param data TOD to request
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
