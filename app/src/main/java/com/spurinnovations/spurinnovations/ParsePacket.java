package com.spurinnovations.spurinnovations;

import android.util.Log;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-15.
 */
public class ParsePacket {

    private Map<TODint, Values> dataMap;
    Map<TODint, Integer> values;

    private static final byte EXTENDED_TOD = (byte) 0x7f;
    private static final byte EXTENDED_OBD = (byte) 0x81;
    private static final int BYTEMASK = 0xff;

    ParsePacket(Map<TODint, Values> dataMap)
    {
        this.dataMap = dataMap;
        ByteToD valueList = new ByteToD();
        values = valueList.getByteToD();
    }

    public void parseData(byte[] buffer)
    {
        int bufferSize = buffer.length;
        int cursor = 0;
        int datasize = 0;
        int ToD = 0;
        byte[] data = new byte[256];

        while(cursor < bufferSize) {

            if (buffer[cursor] <= EXTENDED_TOD)
            {

                datasize = values.get(NormalTOD.valueOf(buffer[cursor] & BYTEMASK));
                ToD = buffer[cursor] & BYTEMASK;

                for(int i = 0; i < datasize; i++)
                {
                    data[i] = buffer[++cursor];
                }

                Values value = new Values(data, datasize);
                dataMap.put(NormalTOD.valueOf(ToD), value);


            }
            else
            {
                switch (buffer[cursor])
                {
                    case EXTENDED_OBD:

                        datasize = values.get(obdTOD.valueOf(buffer[++cursor] & BYTEMASK));
                        ToD = buffer[cursor] & BYTEMASK;

                        for(int i = 0; i < datasize; i++)
                        {
                            data[i] = buffer[++cursor];
                        }

                        Values value = new Values(data, datasize);
                        dataMap.put(obdTOD.valueOf(ToD), value);

                        break;

                    default:

                        Log.d("DEBUG", "UNKNOWN DATA");

                        break;
                }


            }
        }
    }
}
