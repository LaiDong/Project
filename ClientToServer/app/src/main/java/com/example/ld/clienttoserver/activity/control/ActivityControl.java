package com.example.ld.clienttoserver.activity.control;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.about.ActivityAbout;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.battery.ActivityBattery;
import com.example.ld.clienttoserver.activity.battery.ServiceNotification;
import com.example.ld.clienttoserver.activity.devicesetting.ActivityDeviceSetting;
import com.example.ld.clienttoserver.activity.menusetting.ActivitySetting;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.activity.usermanagement.User;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;
import com.example.ld.clienttoserver.widget.circleprogress.ArcProgress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by LD on 7/21/2016.
 */
public class ActivityControl extends AppCompatActivity implements StringRequestActivity.OnRequestStringListener{
    private ProgressDialog progressDialog;
    private StringRequestActivity stringRequestActivity;
    boolean doubleBackToExitPressedOnce = false;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    @InjectView(R.id.link_speed)
    TextView link_speed;
    private final String serviceName = "com.example.ld.clienttoserver.activity.battery.ServiceNotification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_client);
        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.wait));
        ButterKnife.inject(this);

        TextTitle.title("Wi-Fi Hotspot", this);

        if (!isMyServiceRunning()) {
            startService(new Intent(this, ServiceNotification.class));
        }

    }

    @OnClick(R.id.card1)
        void Pin(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            stringRequestActivity.postStringRequest(this, ActivityLogin.url, ToastVar.BATTERY, getString(R.string.battery));
            progressDialog.show();
        }

    }

    @OnClick(R.id.card2)
        void DeviceInfo() {
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            Intent intent = new Intent();
            intent.setClass(this, ActivityDeviceSetting.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
    }

    @OnClick(R.id.card3)
        void Sms(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {

        }
    }

    @OnClick(R.id.card4)
    // change pass
    void user_management(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            stringRequestActivity.postStringRequest(this, ActivityLogin.url, Build.DEVICE+"/"+getIpAddress(), getString(R.string.user_management));
            progressDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting:
                Toast.makeText(this, getString(R.string.ui_menu_setting),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(this, ActivitySetting.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.menu_server:
                ActivityAbout.custom_dialog(this, "IpAddress Server", ActivityLogin.url, R.drawable.app_info);
               break;
        }
        return false;
    }

    @Override
    public void finishLoadJSon(String error, String json) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if(json!=null){
            if(json.contains("pin")){
                String pin[] = json.split("pin");
                String level_battery = pin[1];
                Intent intent = new Intent();
                intent.setClass(this, ActivityBattery.class);
                intent.putExtra("level_battery", level_battery);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }else if(json.trim().contains("user")){
                Intent intent = new Intent(this, User.class);
                intent.putExtra("user_management", json);
                startActivity(intent);
                finish();
            }else{
                Log.e("Warnning!!!","ko co gi");
            }
        }else {
            ToastVar.showToast(this, error);
        }
    }
    private String getIpAddress(){
        WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String ipAddress = Formatter.formatIpAddress(ip);
        return ipAddress;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
            link_speed.setText("Device disconnected");
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            // get link_speed
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            link_speed.setText(connectionInfo.getLinkSpeed()+"Mbps");
        }
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
                stopService(new Intent(getApplicationContext(), ServiceNotification.class));
            }
        }, 2000);
    }
}
