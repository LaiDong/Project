package com.example.ld.displaycamera.ui;

import android.os.Handler;
import android.os.Message;

/**
 * Created by ld on 11/2/16.
 */

public class SleepThread implements Runnable {
    private Handler mMainHandler;
    private int what;
    private long mTime;
    private Object mObject;

    public SleepThread(Handler mainHandler, int what, long mTime, Object mObject) {
        this.mMainHandler = mainHandler;
        this.what = what;
        this.mTime = mTime;
        this.mObject = mObject;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(mTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = mMainHandler.obtainMessage();
        message.what = what;
        message.obj = mObject;
        mMainHandler.sendMessage(message);

    }
}
