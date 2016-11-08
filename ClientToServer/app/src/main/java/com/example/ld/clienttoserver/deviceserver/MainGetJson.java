package com.example.ld.clienttoserver.deviceserver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.devicesetting.ActivityDeviceSetting;
import com.example.ld.clienttoserver.activity.menusetting.ActivitySetting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LD on 7/17/2016.
 */
public class MainGetJson extends AppCompatActivity implements MakeJson.OnFinishDeviceJSonListener {
    ListView list;
    List<Device> deviceList;
    private MakeJson makeJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        list = (ListView) findViewById(R.id.list_deviceinfo);

        Intent intent = getIntent();
        String jsonText = intent.getExtras().getString("jsonText");
        MakeJson.devicejson(jsonText);
        makeJson = new MakeJson();
        makeJson.setOnFinishLoadJSonListener(this);

        TextTitle.title("Device Information",this);

    }

    @Override
    protected void onDestroy() {
//        con.disconnect();
        super.onDestroy();
    }

    @Override
    public void finishDeviceJSon(DeviceJson deviceJson) {
        deviceList = new ArrayList<>();
       /* deviceList.add(new Device("OS Version", deviceJson.getOsVersion()));*/
        deviceList.add(new Device("OS API Level", String.valueOf(deviceJson.getOsApi())));
        deviceList.add(new Device("Battery Level", deviceJson.getBatteryLevel()+ "%"));
        deviceList.add(new Device("DeviceInfo", deviceJson.getDevice()));
        deviceList.add(new Device("Model", deviceJson.getModel()));
        deviceList.add(new Device("IMEI", deviceJson.getImei()));
        deviceList.add(new Device("IMSI", deviceJson.getImsi()));
        deviceList.add(new Device("Software Version", deviceJson.getSoftwareVersion()));
        deviceList.add(new Device("Network Operator ID", deviceJson.getNetworkID()));
        deviceList.add(new Device("Network Operator Name", deviceJson.getNetworkName()));
        if (deviceList != null) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AdapterDevice adapter = new AdapterDevice(deviceList, getApplicationContext());
                    list.setAdapter(adapter);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityDeviceSetting.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
