package com.example.androidhttpserver.main;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidhttpserver.R;
import com.example.androidhttpserver.deviceclient.MainGetjson;
import com.example.androidhttpserver.server.MyServer;
import com.example.androidhttpserver.server.ServiceRunMyServer;
import com.example.androidhttpserver.shareprefmn.SharePrefMn;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by LD on 7/23/2016.
 */
public class Main extends AppCompatActivity {
    private final String serviceName = "com.example.androidhttpserver.server.ServiceRunMyServer";
    public static TextView infoMsg;
    TextView infoIp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoIp = (TextView) findViewById(R.id.infoip);
        infoMsg = (TextView) findViewById(R.id.msg);


        if (!isMyServiceRunning()) {
            Intent it = new Intent(Main.this, ServiceRunMyServer.class);
            startService(it);
        }

        Intent intent = new Intent(Var.DEFAULT_HOSTPOT);
        this.sendBroadcast(intent);
        
        Button btn_device = (Button) findViewById(R.id.btn_deviceClient);
        btn_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyServer.jsonText != null) {
                    Intent i = new Intent(Main.this, MainGetjson.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Client is not logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // set password default
        if (!SharePrefMn.getInstance(getApplicationContext()).isChangePass()) {
            SharePrefMn.getInstance(getApplicationContext()).changePass(true);
            Var.save(this, Var.KEY_PASS, MyServer.change_pass);
        }
    }

    // root access request
    private void acceptRoot(){
        try {
            Process process = Runtime.getRuntime().exec("su");
            PrintWriter pw = new PrintWriter(process.getOutputStream());
            pw.flush();
            pw.close();
            process.waitFor();
            Log.d("Authorities", "root");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        infoIp.setText(MyServer.getIpAddress() + ":" + MyServer.PORT);
    }


}
