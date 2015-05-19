package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * This activity will be called in case of a critical alert and will show
 * an animation based on the type of alert.
 */
public class ImpactAlerts extends Activity {

    TextView alertview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impact_alerts);

        Bundle bundleAlert = getIntent().getExtras();
        int typeofAlert = bundleAlert.getInt("alert");

        alertview = (TextView) findViewById(R.id.alertview);

        switch(typeofAlert)
        {
            case ConstantDefinitions.STOP_CAR:

                alertview.setBackgroundResource(R.drawable.carstop);
                break;

            case ConstantDefinitions.STOP_PEDESTRIAN:

                alertview.setBackgroundResource(R.drawable.pedestop);
                break;

            case ConstantDefinitions.STOP_SPEEDING:

                alertview.setBackgroundResource(R.drawable.stopstop);
                break;

            default:

                Log.d(ConstantDefinitions.TAG, "UNKNOWN ALERT");
                alertview.setBackgroundResource(R.drawable.stopstop);
                break;

        }

        alertview.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable frameAnimation =
                        (AnimationDrawable) alertview.getBackground();
                frameAnimation.start();
            }
        });

        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        ImpactAlerts.this.finish();
                    }
                });
            }
        }, 5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.impact_alerts, menu);
        return true;
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
