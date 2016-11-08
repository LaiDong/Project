package com.example.androidhttpserver.receiver.reboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.androidhttpserver.server.ServiceRunMyServer;

/**
 * Created by LD on 7/27/2016.
 */
public class RebootBroatcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, ServiceRunMyServer.class);
            context.startService(pushIntent);
        }
    }
}
