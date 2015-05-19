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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);
        dataMap = DataMap.getMap();
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
        startActivity(i);
    }

    public void goProfile(View v)
    {
        Intent i = new Intent(this, ProfileView.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void goSettings(View v)
    {
        //Intent i = new Intent(this, SettingsView.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //startActivity(i);
    }

    public void showAddUser(View v)
    {
        LinearLayout adduser = (LinearLayout) findViewById(R.id.AddUser);
        if (adduser.getVisibility() == View.VISIBLE) {
            adduser.setVisibility(View.GONE);
        } else {
            adduser.setVisibility(View.VISIBLE);
        }
    }

    public void showAcceleration(View v)
    {
        LinearLayout addacceleration = (LinearLayout) findViewById(R.id.AddAcceleration);
        if (addacceleration.getVisibility() == View.VISIBLE) {
            addacceleration.setVisibility(View.GONE);
        } else {
            addacceleration.setVisibility(View.VISIBLE);
        }
    }

    public void showAccelerationRate(View v)
    {
        LinearLayout addaccelerationrate = (LinearLayout) findViewById(R.id.AddAccelerationRate);
        if (addaccelerationrate.getVisibility() == View.VISIBLE) {
            addaccelerationrate.setVisibility(View.GONE);
        } else {
            addaccelerationrate.setVisibility(View.VISIBLE);
        }
    }

    public void showSpeed(View v)
    {
        LinearLayout addspeed = (LinearLayout) findViewById(R.id.AddSpeed);
        if (addspeed.getVisibility() == View.VISIBLE) {
            addspeed.setVisibility(View.GONE);
        } else {
            addspeed.setVisibility(View.VISIBLE);
        }
    }
}
