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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainView extends Activity {

    protected EditText speed;
    protected Button showspeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        speed = (EditText) findViewById(R.id.getcurspeed);
        showspeed = (Button) findViewById(R.id.setcurspeed);

        /*IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        final BroadcastReceiver BTReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    //Do something if disconnected
                    showToast("BT Disconnected");
                    Intent goHome = new Intent(getApplicationContext(), MainPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goHome);

                } else {
                    showToast("Nothing Happened");
                }
            }
        };

        this.registerReceiver(BTReceiver, filter);*/

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

    public void setSpeed(View v)
    {
        String speedstring = speed.getText().toString();
        double curspeed = Double.parseDouble(speedstring);

        if(curspeed < 80)
        {
            showspeed.setText(speedstring);
            showspeed.setBackgroundResource(R.drawable.green1);
        }
        else if(curspeed < 120)
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
