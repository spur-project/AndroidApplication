package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * This activity is the Start activity for the application and will show the company
 * logo followed by a list of devices and after selecting one it will connect to the
 * selected one and will send the bluetooth MAC address on connection.
 */
public class MainPage extends Activity implements Runnable{

    protected ProgressBar spinner;
    BluetoothAdapter mBluetoothAdapter;
    //default uuid for serial to bt communication SPP
    private UUID applicationUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    BluetoothDevice mBluetoothDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_page);

        Log.d(ConstantDefinitions.TAG, "Hello World");

        spinner = (ProgressBar)findViewById(R.id.spinnerbar);
        spinner.setVisibility(View.GONE);

        //Setting singleton dataMap object
        Map<TODint, String> dataMap = new HashMap<TODint, String>();
        DataMap.setMap(dataMap);
        ParsePacket packet_parser = new ParsePacket(DataMap.getMap());
        DataMap.setParsingPacket(packet_parser);

        //Setting Map for TOD/byte pairs.
        ByteToD valueM = new ByteToD();
        Map<TODint, Integer> valueMap = valueM.getByteToD();
        ValueMap.setMap(valueMap);

        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {

                        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (mBluetoothAdapter == null)
                        {
                            showToast("No BT Adapter");
                        }
                        else
                        {
                            if (!mBluetoothAdapter.isEnabled())
                            {
                                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableBtIntent, ConstantDefinitions.REQUEST_ENABLE_BT);
                            }
                            else
                            {
                                ListPairedDevices();
                                Intent connectIntent = new Intent(MainPage.this, DeviceList.class);
                                startActivityForResult(connectIntent, ConstantDefinitions.REQUEST_CONNECT_DEVICE);
                            }
                        }
                    }
                });
            }
        }, 1000);

    }

    /**
     * Callback based on the response from the devicelist activity.
     * @param mRequestCode type of device request
     * @param mResultCode result for the activity
     * @param mDataIntent activity intent.
     */
    public void onActivityResult(int mRequestCode, int mResultCode, Intent mDataIntent)
    {
        super.onActivityResult(mRequestCode, mResultCode, mDataIntent);

        switch (mRequestCode)
        {
            case ConstantDefinitions.REQUEST_CONNECT_DEVICE:
                if (mResultCode == Activity.RESULT_OK)
                {
                    Bundle mExtra = mDataIntent.getExtras();
                    String mDeviceAddress = mExtra.getString("DeviceAddress");
                    Log.v(ConstantDefinitions.TAG, "Coming incoming address " + mDeviceAddress);
                    mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(mDeviceAddress);
                    mBluetoothConnectProgressDialog = ProgressDialog.show(this, "Connecting...",
                            mBluetoothDevice.getName() + " : " + mBluetoothDevice.getAddress(), true, false);

                    Thread mBluetoothConnectThread = new Thread(this);
                    mBluetoothConnectThread.start();
                }
                break;

            case ConstantDefinitions.REQUEST_ENABLE_BT:
                if (mResultCode == Activity.RESULT_OK)
                {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(MainPage.this, DeviceList.class);
                    startActivityForResult(connectIntent, ConstantDefinitions.REQUEST_CONNECT_DEVICE);
                }
                else
                {
                    showToast("Error enabling BT");
                }
                break;
        }
    }

    /**
     * List paired devices in the log
     */
    private void ListPairedDevices()
    {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter.getBondedDevices();
        if (mPairedDevices.size() > 0)
        {
            for (BluetoothDevice mDevice : mPairedDevices)
            {
                Log.v(ConstantDefinitions.TAG, "PairedDevices: " + mDevice.getName() + " " + mDevice.getAddress());
            }
        }
    }

    /**
     * Code to execute after bluetooth device has been selected, it will connect to teh device and
     * will send the bluetooth MAC address
     */
    public void run()
    {
        try
        {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(applicationUUID);
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothSocket.connect();
            SocketHandler.setSocket(mBluetoothSocket);

            OutputStream writeOut = SocketHandler.getOStream();
            // unique ID for phones --> In the case of tablets the best option would be to use a 64-bytes generator
            //TelephonyManager tManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            // Simplest approach so far wi-fi has to be on at all times.

            Log.d(ConstantDefinitions.TAG, "Before Sending MAC");
            String deviceId = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE))
                    .getAdapter().getAddress();
            byte[] devID = deviceId.getBytes();
            writeOut.write(devID);
            writeOut.flush();

            Log.d(ConstantDefinitions.TAG, "After Sending MAC");
            mHandler.sendEmptyMessage(0);
        }
        catch (IOException eConnectException)
        {
            Log.d(ConstantDefinitions.TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
    }

    /**
     * In case of an error or timeout the socket will be closed.
     * @param nOpenSocket socket generated for connection
     */
    private void closeSocket(BluetoothSocket nOpenSocket)
    {
        try
        {
            nOpenSocket.close();
            Log.d(ConstantDefinitions.TAG, "SocketClosed");
        }
        catch (IOException ex)
        {
            Log.d(ConstantDefinitions.TAG, "CouldNotCloseSocket");
        }
    }

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            mBluetoothConnectProgressDialog.dismiss();
            showToast("Device Connected");

            Intent goStatus = new Intent(getApplicationContext(), MainView.class);
            goStatus.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goStatus);

            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
