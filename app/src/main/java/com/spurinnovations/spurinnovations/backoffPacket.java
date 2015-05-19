package com.spurinnovations.spurinnovations;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A backoffPacket builder to send trough the network.
 *
 * @author Manuel
 */
public class backoffPacket extends Packet {


    /**
     * Constructor will generate the packet for sendoff
     */
    backoffPacket()
    {
        packet = new byte[ConstantDefinitions.BACKOFF_PACKET_SIZE];
        packet[0] = ConstantDefinitions.START_SEQUENCE;
        packet[1] = ConstantDefinitions.ESCAPE_SEQUENCE;
        packet[2] = ConstantDefinitions.BACKOFF_PACKET_SIZE + 1;
        packet[3] = ConstantDefinitions.BACKOFF_PACKET;
        packet[4] = ConstantDefinitions.END_SEQUENCE;
    }

    /**
     * It will send the packet to the selected output stream
     * @param writeOut OutputStream from the BT socket
     */
    @Override
    public void sendPacket(OutputStream writeOut) {
        try {
            writeOut.write(packet);
            writeOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
