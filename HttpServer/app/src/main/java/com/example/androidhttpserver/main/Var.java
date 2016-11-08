package com.example.androidhttpserver.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by LD on 7/24/2016.
 */
public class Var {
    public static final String KEY_PASS = "pass";
    public static String SHARE_NAME = "setting_pass";
    public static final String PIN = "pin";
    public static final String JSON_DEVICE = "jsondevice";
    public static final String JSON_CLIENT = "jsonclient";
    public static final String FAIL = "fail";
    public static final String SUCCESS = "success";
    public static final String REBOOT_REQUEST = "reboot";
    public static final String CHANGEPASS = "changepass";
    public static final String CONFIGWIFI = "wificonfig";
    public static final String CHANGESSID = "changeSSID";
    public static final String USERMANAGEMENT = "user";
    public static final String CONFIG_WIFI_AP_SSID = "CONFIG_WIFI_AP_SSID";
    public static final String CONFIG_WIFI_AP_ACCOUNT = "CONFIG_WIFI_AP_ACCOUNT";
    public static final String ACTION_REBOOT = "ACTION_REBOOT";
    public static final String DEFAULT_HOSTPOT = "DEFAULT_HOSTPOT";
    public static final String ENABLEMOBILEDATA="mobile_data_enable";
    public static final String DISABLEMOBILEDATA="mobile_data_disable";
    public static final String ENABLEDATAROAMING="data_roaming_enable";
    public static final String DISABLEDATAROAMING="data_roaming_disable";

    public static void showToast(Context context, String sms) {
        Toast.makeText(context, sms, android.widget.Toast.LENGTH_SHORT).show();
    }
    // method for save pass
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
