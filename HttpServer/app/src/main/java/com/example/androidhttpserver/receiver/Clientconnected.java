package com.example.androidhttpserver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ld on 8/19/16.
 */
public class Clientconnected extends BroadcastReceiver {
    private static final String ACTION_CONNECTED_HOTSPOT = "action_connected_hotspot";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(ACTION_CONNECTED_HOTSPOT)){
            String connected = intent.getExtras().getString("connected");
            Log.e("client connected: ", connected);
        }
    }
}
