package com.example.androidhttpserver.deviceclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.androidhttpserver.server.MyServer;
import com.example.androidhttpserver.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LD on 7/17/2016.
 */
public class MainGetjson extends AppCompatActivity{
    ListView list;
    List<DeviceStat> deviceList;
    MyServer myServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        list = (ListView) findViewById(R.id.list_deviceinfo);
       sendGetJson(MyServer.jsonText);
    }

    private void sendGetJson(final String jsonfromclient){
                        deviceList = new ArrayList<DeviceStat>();
                        try {
                            JSONObject jsonObject = new JSONObject(jsonfromclient);
                            String json = jsonObject.toString();
                            Gson gson = new GsonBuilder().create();
                            DeviceJson deviceJson = gson.fromJson(json, DeviceJson.class);

                            deviceList.add(new DeviceStat("OS Version", deviceJson.getOsVersion()));
                            deviceList.add(new DeviceStat("OS API Level", String.valueOf(deviceJson.getOsApi())));
                            deviceList.add(new DeviceStat("Battery Level", deviceJson.getBatteryLevel()));
                            deviceList.add(new DeviceStat("DeviceInfo", deviceJson.getDevice()));
                            deviceList.add(new DeviceStat("Model", deviceJson.getModel()));
                            deviceList.add(new DeviceStat("IMEI", deviceJson.getImei()));
                            deviceList.add(new DeviceStat("IMSI", deviceJson.getImsi()));
                            deviceList.add(new DeviceStat("Software Version", deviceJson.getSoftwareVersion()));
                            deviceList.add(new DeviceStat("Network Operator ID", deviceJson.getNetworkID()));
                            deviceList.add(new DeviceStat("Network Operator Name", deviceJson.getNetworkName()));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (deviceList != null) {
                                        AdapterDevice adapter = new AdapterDevice(deviceList, getApplicationContext());
                                        list.setAdapter(adapter);
                                    }
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
    }

}
