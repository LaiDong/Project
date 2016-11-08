package com.example.ld.clienttoserver.shareprefmn;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ld.clienttoserver.activity.main.ToastVar;

/**
 * Created by LD on 7/24/2016.
 */
public class SharePrefMn {
    static SharePrefMn instance;
    SharedPreferences share;
    Context mContext;
    public SharePrefMn(Context mContext) {
        this.mContext = mContext;
        share = mContext
                .getSharedPreferences(ToastVar.SHARE_NAME, mContext.MODE_MULTI_PROCESS);
    }
    public static SharePrefMn getInstance(Context mContext) {
        if (instance == null) {
            instance = new SharePrefMn(mContext);
        }

        return instance;
    }

    // dk SharedPreferences de changepass
    public void change(boolean isChange, String name) {
        SharedPreferences.Editor edit = share.edit();
        edit.putBoolean(name, isChange);
        edit.commit();

    }

    public boolean isChange(String name) {
        return share.getBoolean(name, false);
    }

    public boolean getStatus(String name, boolean status){
        return share.getBoolean(name, status);
    }
}
