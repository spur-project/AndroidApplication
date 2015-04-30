package com.spurinnovations.spurinnovations;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Manuel on 2015-04-29.
 */

public class SocketHandler {
    private static BluetoothSocket socket;
    private static OutputStream ostream;
    private static InputStream istream;

    public static synchronized BluetoothSocket getSocket(){
        return socket;
    }

    public static synchronized OutputStream getOStream(){
        return ostream;
    }

    public static synchronized InputStream getInSocket(){
        return istream;
    }

    public static synchronized void setSocket(BluetoothSocket socket){
        SocketHandler.socket = socket;
        try {
            SocketHandler.ostream = socket.getOutputStream();
            SocketHandler.istream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
