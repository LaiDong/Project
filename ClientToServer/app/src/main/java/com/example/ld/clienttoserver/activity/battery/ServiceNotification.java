package com.example.ld.clienttoserver.activity.battery;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.about.ActivityAbout;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;

/**
 * Created by ld on 9/8/16.
 */
public class ServiceNotification extends IntentService {
    private Handler mHandler = new Handler();

    public ServiceNotification() {
        super("ServiceNotification");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mHandler.postDelayed(mRunnable, 10*60*1000);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            notification();
            mHandler.postDelayed(mRunnable, 10*60*1000);
        }
    };

    private void notification(){
        if(Integer.valueOf(ActivityLogin.battery) < 15) {
            // notification battery low
          /*  Intent i = new Intent(this, ActivityControl.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder b = new NotificationCompat.Builder(this);

            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_about)
                    .setTicker("Battery Warning!!!")
                    .setContentTitle("Low Battery")
                    .setContentText("Please connect charger.")
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                    .setContentIntent(contentIntent)
                    .setContentInfo("Info");


            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, b.build());*/
            ActivityAbout.custom_dialog(this, "Please connect charger", "The battery is getting low less than 15% remaining.", R.drawable.low_battery);
        }
    }
}
