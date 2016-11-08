package com.example.ld.clienttoserver.activity.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by LD on 7/21/2016.
 */
public class ToastVar {
    public static final String KEY_USER = "nick";
    public static final String KEY_PASS = "pass";
    public static final String KEY_SSID = "ssid";
    public static final String KEY_IP = "ip_ddress";
    public static final String KEY_PASS_SSID = "sharekey";
    public static final String BATTERY = "battery";
    public static final String JSONDEVICE = "json/text";
    public static final String REBOOT = "reboot";
    public static final String CHANGEPASS = "changepass";
    public static final String USERMANAGEMENT = "USERMANAGEMENT";
    public static final String SUCCESS = "success";
    public static final String CONFIGWIFI = "wificonfig";
    public static final String CHANGESSID = "changeSSID";
    public static String SHARE_NAME = "save_ssid";
    public static final String KEY_MOBILE_DATA = "mobiledata";
    public static final String KEY_DATA_ROAMING = "dataroaming";
    public static final String KEY_WIFI_AUTO = "wifiauto";
    public static final String KEY_SMART_POWER = "smartpower";

    public static void showToast(Context context, String sms) {
       Toast.makeText(context, sms, android.widget.Toast.LENGTH_SHORT).show();
    }
    // method for save and get nick and pass user
    public static void save(Context context, String key, String value) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext())
                .edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String get(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, null);
    }

}
