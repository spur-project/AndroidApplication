package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Map;

public class MainView extends Activity implements Runnable{

    protected ByteBuffer mainBuffer;
    private Map<TODint, Values> dataMap;

    private static final String HEXFORMAT = "%02X";
    private static final byte EXTENDED_TOD = (byte) 0xff;
    private static final byte REQUEST_PACKET = (byte) 0x00;
    private static final byte BACKOFF_PACKET = (byte) 0x80;
    private static final byte UPDATE_PACKET = (byte) 0xff;
    private static final byte START_SEQUENCE = (byte) 0x02;
    private static final byte END_SEQUENCE = (byte) 0x04;
    private static final byte ESCAPE_SEQUENCE = (byte) 0x1B;
    private static final int BYTEMASK = 0xff;
    //private static final String STARTSEQUENCE = "02";
    //private static final String ENDSEQUENCE = "04";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        dataMap = DataMap.getMap();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        final BroadcastReceiver BTReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    //Do something if disconnected
                    showToast("BT Disconnected");
                    Intent goHome = new Intent(getApplicationContext(), MainPage.class);
                    goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goHome);

                } else {
                    showToast("Nothing Happened");
                }
            }
        };
        this.registerReceiver(BTReceiver, filter);

        Thread getDataConnected = new Thread(this);
        getDataConnected.start();

        new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            parseData();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_view, menu);
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

    public void goStatus(View v)
    {
        //Intent i = new Intent(this, MainView.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //startActivity(i);
    }

    public void run()
    {
        InputStream istream = SocketHandler.getInSocket();
        //int totalread;
        char c;

        while(true)
        {
            /*try {

                if((istream.available()) > 0) {

                    byte[] wholedata = new byte[1000];

                    while ((totalread = istream.read(wholedata)) > 0) {

                       String received = new String(wholedata, 0, totalread);
                        threadToast(received);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            try {

                byte[] rawdata = new byte[2];

                if((istream.available()) > 0) {
                    while ((istream.read(rawdata, 0, 1)) > -1) {

                        mainBuffer.put(rawdata[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseData() throws InterruptedException {
        byte code;
        int length = 0;
        int packet_type;
        int datasize;
        int ToD;
        byte[] data = new byte[256];
        String hexstring;
        Map<TODint, Integer> values = ValueMap.getMap();

        while(true)
        {
            if(mainBuffer.hasRemaining()) {
                try {
                    code = mainBuffer.get();
                    Thread.sleep(100);
                }
                catch (BufferUnderflowException e)
                {
                    Log.d("Debug", "NO DATA 0");
                    code = (byte) 0x00;
                }
                //hexstring = String.format(HEXFORMAT, code);

                if (code == START_SEQUENCE) {
                    try {
                        code = mainBuffer.get();
                    }
                    catch (BufferUnderflowException e)
                    {
                        Log.d("Debug", "NO DATA 1");
                        code = (byte) 0x00;
                    }

                    length = code & BYTEMASK;

                    try {
                        code = mainBuffer.get();
                    }
                    catch (BufferUnderflowException e)
                    {
                        Log.d("Debug", "NO DATA 2");
                        code = (byte) 0x00;
                    }
                        //packet_type = code & BYTEMASK;

                    switch (code) {
                        case REQUEST_PACKET:

                            break;

                        case BACKOFF_PACKET:

                            break;

                        case UPDATE_PACKET:

                            while (true) {
                                try {
                                    code = mainBuffer.get();
                                }
                                catch (BufferUnderflowException e)
                                {
                                    Log.d("Debug", "NO DATA 3");
                                    code = (byte) 0x00;
                                }
                                //hexstring = String.format(HEXFORMAT, code);
                                ToD = code & BYTEMASK;

                                if (code == END_SEQUENCE) {
                                    break;
                                }

                                if (code != EXTENDED_TOD) {
                                    datasize = values.get(NormalTOD.valueOf(ToD));
                                    mainBuffer.get(data, 0, datasize);
                                    Values value = new Values(data, datasize);
                                    dataMap.put(NormalTOD.valueOf(ToD), value);
                                } else {
                                    try {
                                        code = mainBuffer.get();
                                    }
                                    catch (BufferUnderflowException e)
                                    {
                                        Log.d("Debug", "NO DATA 4");
                                        code = (byte) 0x00;
                                    }
                                    ToD = code & 0xff;
                                    datasize = values.get(NormalTOD.valueOf(ToD));

                                    mainBuffer.get(data, 0, datasize);
                                    Values value = new Values(data, datasize);
                                    dataMap.put(NormalTOD.valueOf(ToD), value);
                                }
                            }

                            break;

                    }
                }
            }

        }
    }

    public void goProfile(View v)
    {
        Intent i = new Intent(this, ProfileView.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void goSettings(View v)
    {
        Intent i = new Intent(this, SettingsView.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void threadToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(MainView.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }


   /* public void setSpeed(double curspeed, double limit, double leeway )
    {
        String speedstring = Double.toString(curspeed);

        if(curspeed < limit)
        {
            showspeed.setText(speedstring);
            showspeed.setBackgroundResource(R.drawable.green1);
        }
        else if(curspeed < limit + leeway / 2)
        {
            showspeed.setText(speedstring);
            showspeed.setBackgroundResource(R.drawable.yellowani);

            showspeed.post(new Runnable() {
                @Override
                public void run() {
                    AnimationDrawable frameAnimation =
                            (AnimationDrawable) showspeed.getBackground();
                    frameAnimation.start();
                }
            });
        }
        else
        {
            showspeed.setText(speedstring);
            showspeed.setBackgroundResource(R.drawable.redani);

            showspeed.post(new Runnable() {
                @Override
                public void run() {
                    AnimationDrawable frameAnimation =
                            (AnimationDrawable) showspeed.getBackground();
                    frameAnimation.start();
                }
            });
        }
    }*/
}
