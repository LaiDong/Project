package com.example.androidhttpserver.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

/**
 * Created by LD on 7/25/2016.
 */
public class ServiceRunMyServer extends Service {
    private MyServer server;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("Service", " onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        try {
            server = new MyServer(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "onStart Service",
                Toast.LENGTH_SHORT).show();
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "onDestroy Service",
                Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
