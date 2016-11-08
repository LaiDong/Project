package com.example.ld.clienttoserver.activity.menusetting;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.about.ActivityAbout;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.account.ActivityAccount;
import com.example.ld.clienttoserver.activity.account.ActivityChangePass;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.activity.network.ActivityNetwork;
import com.example.ld.clienttoserver.activity.hotspot.ActivityWifiConfig;
import com.example.ld.clienttoserver.activity.qrcodescan.QRCodeScan;
import com.example.ld.clienttoserver.request.CheckConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ld on 8/7/16.
 */
public class ActivitySetting extends AppCompatActivity{
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    @InjectView(R.id.layout_setupwifi)
    LinearLayout layout_setupwifi;
    @InjectView(R.id.layout_account)
    LinearLayout layout_account;
    @InjectView(R.id.layout_network)
    LinearLayout layout_network;
    @InjectView(R.id.setup_wifi_tv)
    TextView setup_wifi_tv;
    @InjectView(R.id.setup_account_tv)
    TextView setup_account_tv;
    @InjectView(R.id.setup_tv_wifi)
    TextView setup_wifi;
    @InjectView(R.id.setup_tv_accout)
    TextView setup_account;
    @InjectView(R.id.setup_tv_network)
    TextView setup_network;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ButterKnife.inject(this);

        TextTitle.title("Setting Menu",this);

    }

    @OnClick(R.id.layout_setupwifi)
    void settingWifi(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, ActivityWifiConfig.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    }

    @OnClick(R.id.layout_account)
    void account(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, ActivityAccount.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    }

    @OnClick(R.id.layout_network)
    void network(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            Intent intent = new Intent();
            intent.setClass(this, ActivityNetwork.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    }

    @OnClick(R.id.layout_scan)
    void scan(){
        Intent intent = new Intent(this, QRCodeScan.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @OnClick(R.id.layout_about)
    void about(){
        ActivityAbout.custom_dialog(this, "Application Infomation", "Copyright LaiDong.", R.drawable.app_info);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
            layout_setupwifi.setEnabled(false);
            layout_account.setEnabled(false);
            layout_network.setEnabled(false);
            setup_wifi_tv.setText(getResources().getString(R.string.no_device));
            setup_account_tv.setText(getResources().getString(R.string.not_login));
            setup_account_tv.setTextColor(getResources().getColor(R.color.black_15alpha));
            setup_wifi_tv.setTextColor(getResources().getColor(R.color.black_15alpha));
            setup_wifi.setTextColor(getResources().getColor(R.color.black_15alpha));
            setup_account.setTextColor(getResources().getColor(R.color.black_15alpha));
            setup_network.setTextColor(getResources().getColor(R.color.black_15alpha));
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            layout_setupwifi.setEnabled(true);
            layout_account.setEnabled(true);
            layout_network.setEnabled(true);
            setup_wifi_tv.setText("");
            setup_account_tv.setText("");
            setup_wifi.setTextColor(getResources().getColor(R.color.black_text));
            setup_account.setTextColor(getResources().getColor(R.color.black_text));
            setup_network.setTextColor(getResources().getColor(R.color.black_text));
            WifiManager wifiMgr = (WifiManager)getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            setup_wifi_tv.setText(wifiInfo.getSSID());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityControl.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
