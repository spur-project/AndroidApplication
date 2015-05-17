package com.spurinnovations.spurinnovations;

import android.util.Log;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Manuel on 2015-05-15.
 */
public class ParsePacket {

    private Map<TODint, Values> dataMap;
    private Map<TODint, String> stringMap;
    Map<TODint, Integer> signs;
    Map<TODint, Integer> notification;
    Map<TODint, Integer> values;

    private static final byte EXTENDED_TOD = (byte) 0x7f;
    private static final byte EXTENDED_OBD = (byte) 0x81;
    private static final int BYTEMASK = 0xff;

    ParsePacket(Map<TODint, String> stringMap)
    {
        dataMap = new HashMap<TODint, Values>();
        this.stringMap = stringMap;

        ByteToD valueList = new ByteToD();
        values = valueList.getByteToD();

        signTOD signList = new signTOD();
        signs = signList.getsignTOD();

        notifyTOD notifyList = new notifyTOD();
        notification = notifyList.getnotifyTOD();
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
                putStringMap(NormalTOD.valueOf(ToD), value.getValue(), datasize);
                notification.put(NormalTOD.valueOf(ToD), 1);



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
                        putStringMap(obdTOD.valueOf(ToD), value.getValue(), datasize);
                        notification.put(obdTOD.valueOf(ToD), 1);

                        break;

                    default:

                        Log.d("DEBUG", "UNKNOWN DATA");

                        break;
                }


            }
        }
    }

    private void putStringMap(TODint TOD, byte[] data, int datasize)
    {
        ByteBuffer bytebuffer = ByteBuffer.wrap(data);
        int value = 0;
        short value_short = 0;
        long value_long = 0;


        if(signs.get(TOD) == 0) {
            switch (datasize) {
                case 1:

                    value = bytebuffer.get(0) & BYTEMASK;
                    stringMap.put(TOD, Integer.toString(value));

                    break;
                case 2:

                    value = bytebuffer.getShort(0) & BYTEMASK;
                    stringMap.put(TOD, Integer.toString(value));
                    break;

                case 4:

                    value_long = bytebuffer.getInt(0) & 0xfffffffL;
                    stringMap.put(TOD, Long.toString(value_long));

                    break;

                default:

                    break;
            }
        }
        else if (signs.get(TOD) == 1)
        {
            switch (datasize) {
                /*case 1:

                    value = bytebuffer.get(0) & BYTEMASK;
                    stringMap.put(TOD, Integer.toString(value));

                    break;*/
                case 2:

                    value_short = bytebuffer.getShort(0);
                    stringMap.put(TOD, Short.toString(value_short));
                    break;

                case 4:

                    value = bytebuffer.getInt(0);
                    stringMap.put(TOD, Integer.toString(value));

                    break;

                default:

                    break;
            }
        }
    }

    public List<TODint> getMainViewUpdates() {

        List<TODint> myList = new ArrayList<TODint>();


    }


}
