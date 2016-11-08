package com.example.ld.clienttoserver.activity.network;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.activity.menusetting.ActivitySetting;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;
import com.example.ld.clienttoserver.shareprefmn.SharePrefMn;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ld on 8/8/16.
 */
public class ActivityNetwork extends AppCompatActivity implements StringRequestActivity.OnRequestStringListener{
    @InjectView(R.id.switch_mobidata)
    Switch sw_mobidata;
    @InjectView(R.id.switch_data_roaming)
    Switch sW_data_roaming;
    @InjectView(R.id.data_mobile_tx)
    TextView tv_data_mobile;
    @InjectView(R.id.data_roaming_tx)
    TextView tv_data_roaming;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    private StringRequestActivity stringRequestActivity;
    private static final String ENABLEMOBILEDATA="EnableMobileData";
    private static final String DISABLEMOBILEDATA="DisableMobileData";
    private static final String ENABLEDATAROAMING="EnableDataRoaming";
    private static final String DISABLEDATAROAMING="DisableDataRoaming";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_layout);
        ButterKnife.inject(this);

        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        TextTitle.title("Setting Network",this);

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.ld.clienttoserver.activity.network", MODE_PRIVATE);
        sw_mobidata.setChecked(sharedPrefs.getBoolean(ToastVar.KEY_MOBILE_DATA, false));
        sW_data_roaming.setChecked(sharedPrefs.getBoolean(ToastVar.KEY_DATA_ROAMING, false));


        // mobile data on/off
        sw_mobidata.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(on){
                    tv_data_mobile.setEnabled(true);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.ld.clienttoserver.activity.network", MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_MOBILE_DATA, true);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ENABLEMOBILEDATA, getString(R.string.enable_mobile_data));
                }else{
                    tv_data_mobile.setEnabled(false);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.ld.clienttoserver.activity.network", MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_MOBILE_DATA, false);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, DISABLEMOBILEDATA, getString(R.string.disable_mobile_data));
                }
            }
        });

        // data roaming
        sW_data_roaming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(on){
                    tv_data_roaming.setEnabled(true);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.ld.clienttoserver.activity.network", MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_DATA_ROAMING, true);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ENABLEDATAROAMING, getString(R.string.enable_data_roaming));
                }else {
                    tv_data_roaming.setEnabled(false);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.ld.clienttoserver.activity.network", MODE_PRIVATE).edit();
                    editor.putBoolean(ToastVar.KEY_DATA_ROAMING, false);
                    editor.commit();
                    stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, DISABLEDATAROAMING, getString(R.string.disable_data_roaming));
                }
            }
        });
    }

    @OnClick(R.id.apn_setting_layout)
    void APN_setting(){

    }

    @OnClick(R.id.net_lansetting_layout)
    void lansetting(){

    }


    @Override
    public void finishLoadJSon(String error, String json) {

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivitySetting.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


}
