package com.example.ld.clienttoserver.activity.battery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;
import com.example.ld.clienttoserver.widget.circleprogress.ArcProgress;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ld on 8/8/16.
 */
public class ActivityBattery extends AppCompatActivity implements StringRequestActivity.OnRequestStringListener{
    @InjectView(R.id.arc_progress)
    ArcProgress arcProcess;
    @InjectView(R.id.id_plugin_battery_power_save_tip)
    TextView tv_battery_power_save;
    @InjectView(R.id.id_plugin_battery_auto_wifi_tip)
    TextView tv_battery_auto_wifi;
    @InjectView(R.id.switch_smart_saving)
    Switch sw_smart_saving;
    @InjectView(R.id.switch_wifi_auto)
    Switch sw_wifi_auto;
    private Timer timer;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    private StringRequestActivity stringRequestActivity;

    private static final String ENABLE_AUTO_WIFI="Enable_Auto_Wifi";
    private static final String DISABLE_AUTO_WIFI="Disable_Auto_Wifi";
    private static final String ENABLE_SMART_POWER="Enable_Smart_Power";
    private static final String DISABLE_SMART_POWER="Disable_Smart_Power";
    private static final String packageShare = "com.example.ld.clienttoserver.activity.battery";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        ButterKnife.inject(this);
        Intent intent = getIntent();
        String level_battery = intent.getExtras().getString("level_battery");
        getPin(Integer.valueOf(level_battery));
        tv_battery_power_save.setEnabled(false);
        tv_battery_auto_wifi.setEnabled(false);

        TextTitle.title("Battery Manager", this);

        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        SharedPreferences sharedPrefs = getSharedPreferences(packageShare, MODE_PRIVATE);
        sw_smart_saving.setChecked(sharedPrefs.getBoolean(ToastVar.KEY_SMART_POWER, false));
        sw_wifi_auto.setChecked(sharedPrefs.getBoolean(ToastVar.KEY_WIFI_AUTO, false));

        // smart_saving
       sw_wifi_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(on){
                    tv_battery_auto_wifi.setEnabled(true);
                    SharedPreferences.Editor editor = getSharedPreferences(packageShare, MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_WIFI_AUTO, true);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ENABLE_AUTO_WIFI, getString(R.string.enable_wifi_auto));
                }else {
                    tv_battery_auto_wifi.setEnabled(false);
                    SharedPreferences.Editor editor = getSharedPreferences(packageShare, MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_WIFI_AUTO, false);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, DISABLE_AUTO_WIFI, getString(R.string.disable_wifi_auto));
                }
           }
       });

        // wifi_auto
        sw_smart_saving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(on){
                    tv_battery_power_save.setEnabled(true);
                    SharedPreferences.Editor editor = getSharedPreferences(packageShare, MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_SMART_POWER, true);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ENABLE_SMART_POWER, getString(R.string.enable_smart_power));
                }else {
                    tv_battery_power_save.setEnabled(false);
                    SharedPreferences.Editor editor = getSharedPreferences(packageShare, MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_SMART_POWER, false);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, DISABLE_SMART_POWER, getString(R.string.disable_smart_power));
                }
            }
        });
    }

     void getPin(final int x){
        timer = new Timer();
        arcProcess.setProgress(0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (arcProcess.getProgress() >= x) {
                            timer.cancel();
                        } else {
                            arcProcess.setProgress(arcProcess.getProgress() + 1);
                        }

                    }
                });
            }
        }, 50, 20);
    }

    @Override
    public void finishLoadJSon(String error, String json) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityControl.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
        }
    }

}
