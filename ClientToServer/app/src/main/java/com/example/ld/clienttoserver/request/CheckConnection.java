package com.example.ld.clienttoserver.request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import com.example.ld.clienttoserver.activity.main.ToastVar;

/**
 * Created by ld on 8/15/16.
 */
public class CheckConnection {
    public static boolean getCurrentSsid(Context context) {
        WifiManager wifiMgr = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String ipAddress = Formatter.formatIpAddress(ip);
        String split[] = ipAddress.split("\\.");
        String ipAddress_default = split[0]+"."+split[1]+"."+split[2]+".1";
        return (ipAddress_default.toString().trim().equals(ToastVar.get(context, ToastVar.KEY_IP)));
    }
}
