package com.spurinnovations.spurinnovations;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This activity will show all the settings available to an ADMIN
 * STILL IN PROGRESS, IT WORKS FOR THE LEEWAY ONLY
 */
public class SettingsView extends Activity implements Runnable{

    private Map<TODint, String> dataMap;

    EditText leewayProfile;
    EditText LeewayBreakpoint1;
    EditText LeewayBreakpoint2;
    EditText LeewayBreakpoint3;
    EditText LeewayBreakpoint4;
    EditText LeewayBreakpoint5;
    EditText LeewayBreakpoint6;
    EditText LeewayBreakpoint7;
    EditText LeewayBreakpoint8;
    EditText LeewayBreakpoint9;
    EditText LeewayBreakpoint10;
    EditText LeewayBreakpoint11;
    EditText LeewayValue1;
    EditText LeewayValue2;
    EditText LeewayValue3;
    EditText LeewayValue4;
    EditText LeewayValue5;
    EditText LeewayValue6;
    EditText LeewayValue7;
    EditText LeewayValue8;
    EditText LeewayValue9;
    EditText LeewayValue10;
    EditText LeewayValue11;

    private TODint[] updateRequests = { NormalTOD.SPEED_LEEWAY_PROFILE,
                                        NormalTOD.CUSTOM_SPEED_LEEWAY_BREAKPOINTS,
                                        NormalTOD.CUSTOM_SPEED_LEEWAY_VALUES};

    private static final int ALL_CLICKABLE = 0;
    private static final int LEEWAY_CLICKABLE = 1;
    private static final int ADDUSER_CLICKABLE = 2;
    private static final int ACCELERATION_CLICKABLE = 3;
    private static final int ACCRATE_CLICKABLE = 4;

    private int click_enable;
    private Map<TODint, Values> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);
        dataMap = DataMap.getMap();

        click_enable = ALL_CLICKABLE;

        leewayProfile = (EditText) findViewById(R.id.LeewayProfile);
        LeewayBreakpoint1 = (EditText) findViewById(R.id.leewayBreakpoint1);
        LeewayBreakpoint2 = (EditText) findViewById(R.id.leewayBreakpoint2);
        LeewayBreakpoint3 = (EditText) findViewById(R.id.leewayBreakpoint3);
        LeewayBreakpoint4 = (EditText) findViewById(R.id.leewayBreakpoint4);
        LeewayBreakpoint5 = (EditText) findViewById(R.id.leewayBreakpoint5);
        LeewayBreakpoint6 = (EditText) findViewById(R.id.leewayBreakpoint6);
        LeewayBreakpoint7 = (EditText) findViewById(R.id.leewayBreakpoint7);
        LeewayBreakpoint8 = (EditText) findViewById(R.id.leewayBreakpoint8);
        LeewayBreakpoint9 = (EditText) findViewById(R.id.leewayBreakpoint9);
        LeewayBreakpoint10 = (EditText) findViewById(R.id.leewayBreakpoint10);
        LeewayBreakpoint11 = (EditText) findViewById(R.id.leewayBreakpoint11);
        LeewayValue1 = (EditText) findViewById(R.id.leewayValue1);
        LeewayValue2 = (EditText) findViewById(R.id.leewayValue2);
        LeewayValue3 = (EditText) findViewById(R.id.leewayValue3);
        LeewayValue4 = (EditText) findViewById(R.id.leewayValue4);
        LeewayValue5 = (EditText) findViewById(R.id.leewayValue5);
        LeewayValue6 = (EditText) findViewById(R.id.leewayValue6);
        LeewayValue7 = (EditText) findViewById(R.id.leewayValue7);
        LeewayValue8 = (EditText) findViewById(R.id.leewayValue8);
        LeewayValue9 = (EditText) findViewById(R.id.leewayValue9);
        LeewayValue10 = (EditText) findViewById(R.id.leewayValue10);
        LeewayValue11 = (EditText) findViewById(R.id.leewayValue11);

        Thread getCurrentSettings = new Thread(this);
        getCurrentSettings.start();
        values = DataMap.getParsingPacket().getDataMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_view, menu);
        return true;
    }

    @Override
    public void run() {

        while(true)
        {
            List<TODint> settingUpdates = DataMap.getParsingPacket().getSettingsUpdates();

            if (settingUpdates.size() > 0) {

                for (TODint TOD : settingUpdates) {
                    // data to be shown in mainView
                    Log.d(ConstantDefinitions.TAG, "Got Data to Show");
                    Log.d(ConstantDefinitions.TAG, Integer.toString(TOD.showByteValue()));

                    showData(TOD);
                }
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


                if(values.get(valueTOD) != null) {

                    if (valueTOD == NormalTOD.SPEED_LEEWAY_PROFILE) {

                        Log.d(ConstantDefinitions.TAG, "Leeway Profile");
                        leewayProfile.setText(dataMap.get(NormalTOD.POSTED_SPEED_LIMIT));

                    }

                    if (valueTOD == NormalTOD.CUSTOM_SPEED_LEEWAY_BREAKPOINTS)
                    {
                        byte[] leewaybreakpoints = values.get(NormalTOD.CUSTOM_SPEED_LEEWAY_BREAKPOINTS).getValue().clone();

                        Log.d(ConstantDefinitions.TAG, "Leeway Breakpoints");
                        LeewayBreakpoint1.setText(Integer.toString(leewaybreakpoints[0] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint2.setText(Integer.toString(leewaybreakpoints[1] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint3.setText(Integer.toString(leewaybreakpoints[2] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint4.setText(Integer.toString(leewaybreakpoints[3] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint5.setText(Integer.toString(leewaybreakpoints[4] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint6.setText(Integer.toString(leewaybreakpoints[5] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint7.setText(Integer.toString(leewaybreakpoints[6] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint8.setText(Integer.toString(leewaybreakpoints[7] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint9.setText(Integer.toString(leewaybreakpoints[8] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint10.setText(Integer.toString(leewaybreakpoints[9] & ConstantDefinitions.BYTEMASK));
                        LeewayBreakpoint11.setText(Integer.toString(leewaybreakpoints[10] & ConstantDefinitions.BYTEMASK));

                    }

                    if (valueTOD == NormalTOD.CUSTOM_SPEED_LEEWAY_BREAKPOINTS)
                    {
                        byte[] leewayvalues = values.get(NormalTOD.CUSTOM_SPEED_LEEWAY_VALUES).getValue().clone();

                        Log.d(ConstantDefinitions.TAG, "Leeway Valuess");
                        LeewayValue1.setText(Integer.toString(leewayvalues[0] & ConstantDefinitions.BYTEMASK));
                        LeewayValue2.setText(Integer.toString(leewayvalues[1] & ConstantDefinitions.BYTEMASK));
                        LeewayValue3.setText(Integer.toString(leewayvalues[2] & ConstantDefinitions.BYTEMASK));
                        LeewayValue4.setText(Integer.toString(leewayvalues[3] & ConstantDefinitions.BYTEMASK));
                        LeewayValue5.setText(Integer.toString(leewayvalues[4] & ConstantDefinitions.BYTEMASK));
                        LeewayValue6.setText(Integer.toString(leewayvalues[5] & ConstantDefinitions.BYTEMASK));
                        LeewayValue7.setText(Integer.toString(leewayvalues[6] & ConstantDefinitions.BYTEMASK));
                        LeewayValue8.setText(Integer.toString(leewayvalues[7] & ConstantDefinitions.BYTEMASK));
                        LeewayValue9.setText(Integer.toString(leewayvalues[8] & ConstantDefinitions.BYTEMASK));
                        LeewayValue10.setText(Integer.toString(leewayvalues[9] & ConstantDefinitions.BYTEMASK));
                        LeewayValue11.setText(Integer.toString(leewayvalues[10] & ConstantDefinitions.BYTEMASK));

                    }

                }

                Log.d(ConstantDefinitions.TAG, "Showed Data");

            }
        });
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

    public void sendLeeway (View v)
    {

        int profileFlag = 0;

        EditText[] fields = {LeewayBreakpoint1, LeewayBreakpoint2, LeewayBreakpoint3, LeewayBreakpoint4,
                LeewayBreakpoint5, LeewayBreakpoint6, LeewayBreakpoint7, LeewayBreakpoint8, LeewayBreakpoint9,
                LeewayBreakpoint10, LeewayBreakpoint11, LeewayValue1, LeewayValue2, LeewayValue3, LeewayValue4,
                LeewayValue5, LeewayValue6, LeewayValue7, LeewayValue8, LeewayValue9 ,LeewayValue10, LeewayValue11};

        if(TextUtils.isEmpty(leewayProfile.getText().toString()))
        {
            profileFlag = 0;
        }
        else
        {
            if(leewayProfile.getText().toString().length() > 1)
            {
                Toast.makeText(SettingsView.this, "Profile can only be a value between 0-3", Toast.LENGTH_SHORT).show();
                return;
            }
            profileFlag = 1;
        }

        if(profileFlag == 0) {
            for (EditText field : fields) {
                if (TextUtils.isEmpty(field.getText().toString())) {
                    Toast.makeText(SettingsView.this, "Please fill in all the values", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            for (EditText field : fields) {
                if (Integer.parseInt(field.getText().toString()) > 255 ||
                        Integer.parseInt(field.getText().toString()) < 0)
                {
                    Toast.makeText(SettingsView.this, "Values can only be between 0-255", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        updatePacket  packetUpdate= new updatePacket();

        if(profileFlag == 1)
        {
            packetUpdate.addData((byte) NormalTOD.SPEED_LEEWAY_PROFILE.showByteValue());
            packetUpdate.addData((byte) Integer.parseInt(leewayProfile.getText().toString()));
            packetUpdate.sendPacket(SocketHandler.getOStream());
        }
        else
        {
            int i =0;

            packetUpdate.addData((byte) NormalTOD.CUSTOM_SPEED_LEEWAY_BREAKPOINTS.showByteValue());

            for (EditText field : fields) {

                if(i == 11)
                {
                    packetUpdate.addData((byte) NormalTOD.CUSTOM_SPEED_LEEWAY_VALUES.showByteValue());
                }

                packetUpdate.addData((byte) Integer.parseInt(field.getText().toString()));

                i++;
            }

            packetUpdate.sendPacket(SocketHandler.getOStream());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ConstantDefinitions.TAG, "Before Sending Request SETTINGS Packet");

        requestPacket requestUpdates = new requestPacket();
        requestUpdates.setTypeofRequest(ConstantDefinitions.SINGLE_REQUEST_TYPE);

        for(TODint TOD : updateRequests) {
            requestUpdates.addData((byte)TOD.showByteValue());
        }

        Log.d(ConstantDefinitions.TAG, "REQUEST SETTINGS READY");

        requestUpdates.sendPacket(SocketHandler.getOStream());

        Log.d(ConstantDefinitions.TAG, "After Sending Request SETTINGS Packet");

    }
}
