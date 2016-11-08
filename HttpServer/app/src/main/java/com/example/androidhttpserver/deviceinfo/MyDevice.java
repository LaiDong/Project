package com.example.androidhttpserver.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by LD on 7/17/2016.
 */
public class MyDevice {

    public static String getDeviceInfo(Context mContext){
        String serviceName = mContext.TELEPHONY_SERVICE;
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(serviceName);
        String s = "DeviceStat-infos:";
        s+="\n OS Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")";
        s += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
        s += "\n DeviceStat: " + android.os.Build.DEVICE;
        s += "\n Model (and Product): " + android.os.Build.MODEL + " ("+ android.os.Build.PRODUCT + ")";
        s += "\n IMEI: " + telephonyManager.getDeviceId(); // so nhan dang thiet bi di dong
        s += "\n IMSI: " + telephonyManager.getSubscriberId(); // get giua so IMEI va sim
        s += "\n Software Version: " + telephonyManager.getDeviceSoftwareVersion();
        s += "\n Network operator ID: " + telephonyManager.getNetworkOperator();
        s += "\n Network operator name: " + telephonyManager.getNetworkOperatorName();
        s += "\n Battery Level: " + getBatteryLevel(mContext);
        return s;
    }

    public static String getBatteryLevel(Context mContext){
        Intent intent  = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int    level   = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int    scale   = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int    percent = (level*100)/scale;
        return String.valueOf(percent);
    }

}
