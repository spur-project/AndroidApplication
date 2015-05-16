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

    protected ByteBufferSem mainBuffer;
    private Map<TODint, Values> dataMap;

    private static final int MAX_PACKET_SIZE = 256;

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
                            showData();
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
        mainBuffer = new ByteBufferSem(MAX_PACKET_SIZE);
        ValidatePacket packetValidator = new ValidatePacket();
        ParsePacket packetparser = new ParsePacket(dataMap);

        char c;

        while(true)
        {
            try {

                int rawdata_int;
                byte rawdata;

                if((istream.available()) > 0)
                {
                    while ((rawdata_int = istream.read()) > -1)
                    {
                        rawdata = (byte) rawdata_int;
                        mainBuffer.write(rawdata);

                        if(mainBuffer.isReady())
                        {
                            if(packetValidator.validate(mainBuffer.getData(), mainBuffer.getElementsNumber()))
                            {
                                packetparser.parseData(packetValidator.getValidatedPacket());
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showData()
    {

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
