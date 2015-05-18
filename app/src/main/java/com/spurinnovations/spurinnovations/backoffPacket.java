package com.spurinnovations.spurinnovations;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Manuel on 2015-05-17.
 */
public class backoffPacket extends Packet {



    backoffPacket()
    {
        packet = new byte[ConstantDefinitions.BACKOFF_PACKET_SIZE];
        packet[0] = ConstantDefinitions.START_SEQUENCE;
        packet[1] = ConstantDefinitions.ESCAPE_SEQUENCE;
        packet[2] = ConstantDefinitions.BACKOFF_PACKET_SIZE + 1;
        packet[3] = ConstantDefinitions.BACKOFF_PACKET;
        packet[4] = ConstantDefinitions.END_SEQUENCE;
    }

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
