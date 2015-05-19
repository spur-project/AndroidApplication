package com.spurinnovations.spurinnovations;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Singleton and global class to refer to the bluetooth socket created for connection
 * @author Manuel
 */

public class SocketHandler {
    private static BluetoothSocket socket;
    private static OutputStream ostream;
    private static InputStream istream;

    /**
     *
     * @return socket object
     */
    public static synchronized BluetoothSocket getSocket(){
        return socket;
    }

    /**
     *
     * @return outputstream for the socket
     */
    public static synchronized OutputStream getOStream(){
        return ostream;
    }

    /**
     *
     * @return inputstream for the socket
     */
    public static synchronized InputStream getInSocket(){
        return istream;
    }

    /**
     * This class will set the object and will instantiate the in/out stream
     * @param socket socket to set for the SocketHandler object.
     */
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
