package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public class MainView extends Activity implements Runnable{

    protected EditText speed;
    protected Button showspeed;
    protected ByteBuffer mainBuffer;
    private Map<TODint, Values> dataMap;

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
                        parseData();
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

/*            try {

                byte[] rawdata = new byte[2];

                if((istream.available()) > 0) {
                    while ((istream.read(rawdata, 0, 1)) > -1) {

                        mainBuffer.put(rawdata[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void parseData()
    {
       /* byte code;
        String hexstring;
        int length = 0;
        int packet_type;
        int ToD;
        byte[] data = new byte[256];

        while(true)
        {
            code = mainBuffer.get();
            hexstring = String.format("%02X ", code);

            if(hexstring.equalsIgnoreCase("69"))
            {
                code = mainBuffer.get();
                length = code & 0xff;

                code = mainBuffer.get();
                packet_type = code & 0xff;

                switch(packet_type)
                {
                    case 0:

                        break;

                    case 255:

                        code = mainBuffer.get();
                        ToD = code & 0xff;

                        if(ToD != 255)
                        {
                            mainBuffer.get(data, 0, length - 3);
                            Values value = new Values(data, length-3);
                            dataMap.put(NormalTOD.valueOf(ToD), value);
                        }
                        else
                        {
                            code = mainBuffer.get();
                            ToD = code & 0xff;

                                mainBuffer.get(data, 0, length - 4);
                                Values value = new Values(data, length - 4);
                                dataMap.put(NormalTOD.valueOf(ToD), value);
                        }
                        break;

                }
            }
        }*/
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


    public void setSpeed(double curspeed, double limit, double leeway )
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
    }
}
