package com.example.ld.clienttoserver.activity.hotspot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.main.MainActivity;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.activity.menusetting.ActivitySetting;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by LD on 7/27/2016.
 */
public class ActivityWifiConfig extends AppCompatActivity implements StringRequestActivity.OnRequestStringListener {
    private StringRequestActivity stringRequestActivity;
    private String ssidName, shareKey;
    private ProgressDialog progressDialog;
    private boolean check = false;

    @InjectView(R.id.edt_ssid)
    EditText edt_ssid;
    @InjectView(R.id.edt_sharekey)
    EditText edt_sharekey;
    @InjectView(R.id.cb_sercurity)
    CheckBox cb_sercurity;
    @InjectView(R.id.cb_showpass)
    CheckBox cb_showpass;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wificonfig);
        ButterKnife.inject(this);

        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.wait));

        TextTitle.title("Wi-Fi Config", this);

        ssidName = ToastVar.get(this, ToastVar.KEY_SSID);
        shareKey = ToastVar.get(this, ToastVar.KEY_PASS_SSID);

        if(ssidName!=null){
            edt_ssid.setText(ssidName);
            edt_sharekey.setText(shareKey);
            check = true;
        }
    }

   @OnClick(R.id.cb_sercurity)
    void check_sercurity(){
       if(cb_sercurity.isChecked()){
           edt_sharekey.setVisibility(View.VISIBLE);
           cb_showpass.setVisibility(View.VISIBLE);
       }else {
           edt_sharekey.setVisibility(View.INVISIBLE);
           cb_showpass.setVisibility(View.INVISIBLE);
       }
   }

    @OnClick(R.id.cb_showpass)
     void check_showpass(){
        if(cb_showpass.isChecked()){
            edt_sharekey.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else {
            edt_sharekey.setInputType(129);
        }
    }

   @OnClick(R.id.btn_save)
    void btn_save(){
       if(!CheckConnection.getCurrentSsid(this)){
           bottom_panel.setVisibility(View.VISIBLE);
       }else {
           bottom_panel.setVisibility(View.INVISIBLE);
           Save();
       }
   }

    @OnClick(R.id.btn_exit)
     void btn_exit(){
        Intent intent = new Intent(this, ActivitySetting.class);
        startActivity(intent);
        finish();
    }

    private void Save(){
        ssidName = edt_ssid.getText().toString();
        shareKey = edt_sharekey.getText().toString();

        // not enter ssid name
        if (ssidName.length() == 0) {
            edt_ssid.requestFocus();
            ToastVar.showToast(this, this.getResources().getString(R.string.enter_ssid));
            return;
        }
        // not enter pass
        if(edt_sharekey.isShown()) {
            if (shareKey.length() == 0) {
                edt_sharekey.requestFocus();
                ToastVar.showToast(this, this.getResources().getString(R.string.enter_pass));
                return;
            }else if(shareKey.length()<8){
                edt_sharekey.requestFocus();
                ToastVar.showToast(this, this.getResources().getString(R.string.condition_length_pass));
                return;
            }
        }

        ToastVar.save(this, ToastVar.KEY_SSID, ssidName);
        ToastVar.save(this, ToastVar.KEY_PASS_SSID, shareKey);

        if(ssidName!=null && edt_sharekey.isShown()){
            stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ToastVar.CONFIGWIFI ,ssidName +"#"+ shareKey);
            ToastVar.showToast(this, "Tether hostpot have been change");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(ssidName!=null && !edt_sharekey.isShown()){
            stringRequestActivity.postStringRequest(getApplicationContext(), ActivityLogin.url, ToastVar.CHANGESSID ,ssidName);
         //   progressDialog.show();
            ToastVar.showToast(this, "Tether hostpot have been change");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void finishLoadJSon(String error, String json) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if(json!=null){
            if(json.trim().equals(ToastVar.SUCCESS)){
                ToastVar.showToast(this, this.getResources().getString(R.string.wificonfig));
            }else{
                ToastVar.showToast(this, this.getResources().getString(R.string.wificonfig_fail));
            }
        }else {
            ToastVar.showToast(this, error);
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivitySetting.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
}
