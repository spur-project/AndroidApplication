package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class MainView extends Activity implements Runnable{

    protected ByteBufferSem mainBuffer;
    private Map<TODint, String> dataMap;
    ParsePacket packetparser;

    private TODint[] updateRequests = { NormalTOD.POSTED_SPEED_LIMIT,
                                        NormalTOD.VEHICLE_SPEED,
                                        NormalTOD.VEHICLE_SPEED,
                                        NormalTOD.ELAPSED_TIME_SPEED_LIMIT,
                                        NormalTOD.ELAPSED_TIME_SPEED_LIMIT,
                                        NormalTOD.VEHICLE_ACCELERATION,
                                        NormalTOD.VEHICLE_CORNERING_ACCELERATION};

    private TextView speed_limit;
    private TextView vehicle_speed;
    private TextView time_elapsed;
    private TextView braking_acc;
    private TextView forward_acc;
    private TextView cornering_acc;
    private TextView alert_text;

    private int current_limit;
    private int current_leeway;

    private static final int MAX_PACKET_SIZE = 256;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        Log.d(ConstantDefinitions.TAG, "Starting Main View");

        dataMap = DataMap.getMap();
        packetparser = DataMap.getParsingPacket();
        current_limit = 0;

        //just for testing
        current_leeway = 20;

        alert_text = (TextView) findViewById(R.id.Alert);
        speed_limit = (TextView) findViewById(R.id.SpeedLimit);
        vehicle_speed = (TextView) findViewById(R.id.curSpeed);
        time_elapsed = (TextView) findViewById(R.id.Timer);
        braking_acc = (TextView) findViewById(R.id.Braking);
        forward_acc = (TextView) findViewById(R.id.Acceleration);
        cornering_acc = (TextView) findViewById(R.id.Cornering);

        Log.d(ConstantDefinitions.TAG, "Before Sending Request Packet");

        requestPacket requestUpdates = new requestPacket();

        Log.d(ConstantDefinitions.TAG, "REQUEST GETTING READY");

        for(TODint TOD : updateRequests) {
            requestUpdates.addData((byte)TOD.showByteValue());
        }

        Log.d(ConstantDefinitions.TAG, "REQUEST READY");

        requestUpdates.sendPacket(SocketHandler.getOStream());

        Log.d(ConstantDefinitions.TAG, "After Sending Request Packet");

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        /*final BroadcastReceiver BTReceiver = new BroadcastReceiver() {
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
        this.registerReceiver(BTReceiver, filter);*/

        Thread getDataConnected = new Thread(this);
        getDataConnected.start();
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
                            Log.d(ConstantDefinitions.TAG, "Buffer is Ready");

                            if(packetValidator.validate(mainBuffer.getData(), mainBuffer.getElementsNumber()))
                            {
                                Log.d(ConstantDefinitions.TAG, "Packet is Valid");
                                packetparser.parseData(packetValidator.getValidatedPacket());

                                Log.d(ConstantDefinitions.TAG, "Data has been parsed");

                                List<TODint> mainUpdates =packetparser.getMainViewUpdates();

                                if(mainUpdates.size() > 0) {
                                    Log.d(ConstantDefinitions.TAG, "Data TO SHOW");

                                    for (TODint TOD : mainUpdates) {

                                        Log.d(ConstantDefinitions.TAG, "Got Data to Show");
                                        Log.d(ConstantDefinitions.TAG, Integer.toString(TOD.showByteValue()));

                                        showData(TOD);
                                    }
                                }

                                Log.d(ConstantDefinitions.TAG, "Data is Showing");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showData(final TODint valueTOD)
    {
//        Log.d(ConstantDefinitions.TAG, "Got Data to Show");
//        Log.d(ConstantDefinitions.TAG, Integer.toString(valueTOD.showByteValue()));

        runOnUiThread(new Runnable() {
            public void run()
            {


                if(dataMap.get(valueTOD) != null) {

                    if (valueTOD == NormalTOD.POSTED_SPEED_LIMIT) {

                        current_limit = Integer.valueOf(dataMap.get(NormalTOD.POSTED_SPEED_LIMIT));
                        speed_limit.setText(Integer.toString(current_limit));

                    }

                    if (valueTOD == NormalTOD.VEHICLE_SPEED) {

                        int current_speed = Integer.valueOf(dataMap.get(NormalTOD.VEHICLE_SPEED));
                        vehicle_speed.setText(Integer.toString(current_speed));

                        if(current_limit > 0) {

                            if (current_speed <= current_limit) {

                                vehicle_speed.setBackgroundResource(R.drawable.green1);
                                alert_text.setText("Everything is Good, just cruisin'");

                            } else if (current_speed <= current_limit + current_leeway / 2) {

                                vehicle_speed.setBackgroundResource(R.drawable.yellowani);
                                alert_text.setText("Over the Speed Limit");

                                vehicle_speed.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        AnimationDrawable frameAnimation =
                                                (AnimationDrawable) vehicle_speed.getBackground();
                                        frameAnimation.start();
                                    }
                                });
                            }
                            else if(current_speed <= current_limit + current_leeway)
                            {

                                vehicle_speed.setBackgroundResource(R.drawable.yellowani);
                                alert_text.setText("Please Slow Down");

                                vehicle_speed.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        AnimationDrawable frameAnimation =
                                                (AnimationDrawable) vehicle_speed.getBackground();
                                        frameAnimation.start();
                                    }
                                });
                            }
                            else if(current_speed <= current_limit + current_leeway * 2)
                            {
                                vehicle_speed.setBackgroundResource(R.drawable.redani);
                                alert_text.setText("Going Past Leeway");

                                vehicle_speed.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        AnimationDrawable frameAnimation =
                                                (AnimationDrawable) vehicle_speed.getBackground();
                                        frameAnimation.start();
                                    }
                                });
                            }
                            else
                            {
                                Intent goAlert = new Intent(MainView.this, ImpactAlerts.class);
                                alert_text.setText("SLOW DOWN NOW");
                                vehicle_speed.setBackgroundResource(R.drawable.redani);

                                vehicle_speed.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        AnimationDrawable frameAnimation =
                                                (AnimationDrawable) vehicle_speed.getBackground();
                                        frameAnimation.start();
                                    }
                                });

                                goAlert.putExtra("alert", ConstantDefinitions.STOP_PEDESTRIAN);
                                startActivity(goAlert);
                            }
                        }
                    }

                    if (valueTOD == NormalTOD.ELAPSED_TIME_SPEED_LIMIT) {

                        time_elapsed.setText(dataMap.get(NormalTOD.ELAPSED_TIME_SPEED_LIMIT));
                    }

                    if (valueTOD == NormalTOD.VEHICLE_ACCELERATION) {
                        short acceleration = Short.valueOf(dataMap.get(NormalTOD.VEHICLE_ACCELERATION));
                        int braking = acceleration * -1;
                        braking_acc.setText(Integer.toString(braking));
                        forward_acc.setText(Short.toString(acceleration));
                    }

                    if (valueTOD == NormalTOD.VEHICLE_CORNERING_ACCELERATION) {
                        cornering_acc.setText(dataMap.get(NormalTOD.VEHICLE_CORNERING_ACCELERATION));
                    }
                }

                Log.d(ConstantDefinitions.TAG, "Showed Data");

            }
        });
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


    }*/
}
