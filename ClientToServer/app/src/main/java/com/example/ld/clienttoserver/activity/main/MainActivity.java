package com.example.ld.clienttoserver.activity.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.bandwidth.Checkbandwidth;
import com.example.ld.clienttoserver.shareprefmn.SharePrefMn;


public class MainActivity extends AppCompatActivity {
//    private AlertDialog progressDialog;
    private ProgressDialog progressDialog;
    public static final String CurrentSSID = "Wifi-hostpot_vnpt";
    int count =1;
    boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   //     progressDialog = new SpotsDialog(this, R.style.Custom);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.wait));
        progressDialog.setMessage("Task in progress ...");

        // set ssid default
        if (!SharePrefMn.getInstance(getApplicationContext()).isChange(ToastVar.KEY_SSID)) {
            SharePrefMn.getInstance(getApplicationContext()).change(true, ToastVar.KEY_SSID);
            ToastVar.save(this, ToastVar.KEY_SSID, CurrentSSID);
        }

        TextTitle.title("Wi-Fi Hotspot", this);

        Intent intent = new Intent(this, Checkbandwidth.class);
        startService(intent);
    }

    private class MyTask extends AsyncTask<Integer, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (mWifi.isConnected()) {
             //     check = getCurrentSsid(getApplicationContext());
                check = true;
            }
            return check;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            boolean bResponse = result;
            if(bResponse==true){
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }

        }
    }

    public static boolean getCurrentSsid(Context context) {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        }
        return (ssid.contains(ToastVar.get(context, ToastVar.KEY_SSID))||ssid.contains(CurrentSSID));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        count =1;
        MyTask myTask = new MyTask();
        myTask.execute(3);
        check = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Activity: ", "Destroy");
    }

}
