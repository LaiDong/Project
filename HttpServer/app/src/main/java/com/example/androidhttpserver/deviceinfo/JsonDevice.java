package com.example.androidhttpserver.deviceinfo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.JsonWriter;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by LD on 7/17/2016.
 */
public class JsonDevice {
    // 2, ghi doi tuong java thanh du lieu json vao Writer
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void writeJson(Writer output, Device device) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);
        jsonWriter.beginObject();

        jsonWriter.name("osVersion").value(device.getOsVersion());
        jsonWriter.name("osApi").value(device.getOsApi());
        jsonWriter.name("device").value(device.getDevice());
        jsonWriter.name("model").value(device.getModel());
        jsonWriter.name("imei").value(device.getImei());
        jsonWriter.name("imsi").value(device.getImsi());
        jsonWriter.name("softwareVersion").value(device.getSoftwareVersion());
        jsonWriter.name("networkID").value(device.getNetworkID());
        jsonWriter.name("networkName").value(device.getNetworkName());
        jsonWriter.name("batteryLevel").value(device.getBatteryLevel());
        jsonWriter.endObject();
    }

    //1, get deviceinfo
    public static Device getDevice(Context mContext){
        Device device = new Device();
        String serviceName = mContext.TELEPHONY_SERVICE;
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(serviceName);
        device.setOsVersion(System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")");
        device.setOsApi(android.os.Build.VERSION.SDK_INT);
        device.setDevice(android.os.Build.DEVICE);
        device.setModel(android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")");
        device.setImei(telephonyManager.getDeviceId());
        device.setImsi(telephonyManager.getSubscriberId());
        device.setSoftwareVersion(telephonyManager.getDeviceSoftwareVersion());
        device.setNetworkID(telephonyManager.getNetworkOperator());
        device.setNetworkName(telephonyManager.getNetworkOperatorName());
        device.setBatteryLevel(MyDevice.getBatteryLevel(mContext));
        return device;
    }
}
