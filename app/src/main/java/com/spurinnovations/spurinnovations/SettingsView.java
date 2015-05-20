package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Map;

/**
 * This activity will show all the settings available to an ADMIN
 * STILL IN PROGRESS, GUI IS SHOWING BUT NOT FUNCTIONAL
 */
public class SettingsView extends Activity {

    private Map<TODint, String> dataMap;

    private static final int ALL_CLICKABLE = 0;
    private static final int LEEWAY_CLICKABLE = 1;
    private static final int ADDUSER_CLICKABLE = 2;
    private static final int ACCELERATION_CLICKABLE = 3;
    private static final int ACCRATE_CLICKABLE = 4;
    private static final int SPEED_CLICKABLE = 5;

    private int click_enable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);
        dataMap = DataMap.getMap();

        click_enable = ALL_CLICKABLE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_view, menu);
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

    public void goStatus(View v)
    {
        Intent i = new Intent(this, MainView.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    public void goProfile(View v)
    {
        Intent i = new Intent(this, ProfileView.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    public void goSettings(View v)
    {
        //Intent i = new Intent(this, SettingsView.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //startActivity(i);
    }

    public void showLeeway(View v)
    {
        if(click_enable == ALL_CLICKABLE || click_enable == LEEWAY_CLICKABLE) {
            LinearLayout leeway = (LinearLayout) findViewById(R.id.leewayLayout);
            if (leeway.getVisibility() == View.VISIBLE) {
                leeway.setVisibility(View.GONE);
                click_enable = ALL_CLICKABLE;
            } else {
                leeway.setVisibility(View.VISIBLE);
                click_enable = LEEWAY_CLICKABLE;
            }
        }
    }

    public void showAddUser(View v)
    {
        if(click_enable == ALL_CLICKABLE || click_enable == ADDUSER_CLICKABLE) {
            LinearLayout adduser = (LinearLayout) findViewById(R.id.AddUser);
            if (adduser.getVisibility() == View.VISIBLE) {
                adduser.setVisibility(View.GONE);
                click_enable = ALL_CLICKABLE;
            } else {
                adduser.setVisibility(View.VISIBLE);
                click_enable = ADDUSER_CLICKABLE;
            }
        }
    }

    public void showAcceleration(View v)
    {
        if(click_enable == ALL_CLICKABLE || click_enable == ACCELERATION_CLICKABLE) {
            LinearLayout addacceleration = (LinearLayout) findViewById(R.id.AddAcceleration);
            if (addacceleration.getVisibility() == View.VISIBLE) {
                addacceleration.setVisibility(View.GONE);
                click_enable = ALL_CLICKABLE;
            } else {
                addacceleration.setVisibility(View.VISIBLE);
                click_enable = ACCELERATION_CLICKABLE;

            }
        }
    }

    public void showAccelerationRate(View v)
    {
        if(click_enable == ALL_CLICKABLE || click_enable == ACCRATE_CLICKABLE) {
            LinearLayout addaccelerationrate = (LinearLayout) findViewById(R.id.AddAccelerationRate);
            if (addaccelerationrate.getVisibility() == View.VISIBLE) {
                addaccelerationrate.setVisibility(View.GONE);
                click_enable = ALL_CLICKABLE;
            } else {
                addaccelerationrate.setVisibility(View.VISIBLE);
                click_enable = ACCRATE_CLICKABLE;
            }
        }
    }

    public void showSpeed(View v)
    {
        if(click_enable == ALL_CLICKABLE || click_enable == SPEED_CLICKABLE) {
            LinearLayout addspeed = (LinearLayout) findViewById(R.id.AddSpeed);
            if (addspeed.getVisibility() == View.VISIBLE) {
                addspeed.setVisibility(View.GONE);
                click_enable = ALL_CLICKABLE;
            } else {
                addspeed.setVisibility(View.VISIBLE);
                click_enable = SPEED_CLICKABLE;

            }
        }
    }
}
