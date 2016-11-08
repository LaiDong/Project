package com.example.ld.clienttoserver.activity.devicesetting;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.deviceserver.MainGetJson;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ld on 8/8/16.
 */
public class ActivityDeviceSetting extends AppCompatActivity implements StringRequestActivity.OnRequestStringListener{
    private ProgressDialog progressDialog;
    StringRequestActivity stringRequestActivity;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_setting_layout);
        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.wait));
        ButterKnife.inject(this);

        TextTitle.title("Setting Device", this);

    }

    @OnClick(R.id.id_deviceinfo)
    void Deviceinfo(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            stringRequestActivity.postStringRequest(this, ActivityLogin.url, ToastVar.JSONDEVICE, getString(R.string.json_device));
            progressDialog.show();
        }

    }

    @OnClick(R.id.id_update_software)
    void UpdateSystemSoftware(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.id_reboot)
    void Reboot(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDeviceSetting.this);
            builder.setTitle("Reboot device")
                    .setMessage("Are you sure want to reboot device?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ToastVar.REBOOT, getString(R.string.reboot));
                            ToastVar.showToast(ActivityDeviceSetting.this, "Device server have been reboot!!!");
                        }
                    });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    }

    @Override
    public void finishLoadJSon(String error, String json) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if(json!=null){
            if(json.contains("osVersion")){
                String jsons[] = json.split("\\{");
                Intent i = new Intent(this, MainGetJson.class);
                i.putExtra("jsonText", json);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }else if(json.contains("reboot")){
                ToastVar.showToast(this, this.getResources().getString(R.string.reboot_device));
            }else{
        Log.e("Warnning!!!","ko co gi");
    }
   }else {
            ToastVar.showToast(this, error);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityControl.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
