package com.example.androidhttpserver.wificonfig;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.lang.reflect.Method;

/**
 * Created by LD on 7/27/2016.
 */
public class WifiConfig {

    // change ssid name
    public static boolean setHotspotName(String newName, Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
            Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
            WifiConfiguration wifiConfig = (WifiConfiguration) getConfigMethod.invoke(wifiManager);

            wifiConfig.SSID = newName;

            Method setConfigMethod = wifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
            setConfigMethod.invoke(wifiManager, wifiConfig);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // chang user+pass rs hostpost
    public static boolean setAcountName(String nameSSID, String password, Context mContext){
        try {
            WifiManager wifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
            Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
            WifiConfiguration wifiConfig = (WifiConfiguration) getConfigMethod.invoke(wifiManager);

            if(wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(false);
            }

            wifiConfig.SSID = nameSSID;
            wifiConfig.preSharedKey = password;

            wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            Method setConfigMethod = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            setConfigMethod.invoke(wifiManager, wifiConfig, true);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
