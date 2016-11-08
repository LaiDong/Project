package com.example.androidhttpserver.shareprefmn;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidhttpserver.main.Var;

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
                .getSharedPreferences(Var.SHARE_NAME, mContext.MODE_MULTI_PROCESS);
    }
    public static SharePrefMn getInstance(Context mContext) {
        if (instance == null) {
            instance = new SharePrefMn(mContext);
        }

        return instance;
    }

    // dk SharedPreferences de changepass
    public void changePass(boolean isChangePass) {
        SharedPreferences.Editor edit = share.edit();
        edit.putBoolean(Var.KEY_PASS, isChangePass);
        edit.commit();

    }

    public boolean isChangePass() {
        return share.getBoolean(Var.KEY_PASS, false);
    }
}
