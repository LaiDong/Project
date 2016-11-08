package com.example.ld.clienttoserver.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.deviceinfo.DeviceInfo;
import com.example.ld.clienttoserver.deviceinfo.JsonDevice;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.activity.main.MainActivity;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;
import com.example.ld.clienttoserver.shareprefmn.SharePrefMn;

import java.io.IOException;
import java.io.StringWriter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LD on 7/20/2016.
 */
public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, StringRequestActivity.OnRequestStringListener {
    boolean check = false;
    Button btn_login;
    EditText edtUser, edtPass;
    private String user, pass;
    public static String url= "";
    private ProgressDialog progressDialog;
    private StringRequestActivity stringRequestActivity;
    private CheckBox cbRememberPass;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    public static String battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        TextTitle.title("Login", this);

           check = true;
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btn_login = (Button) findViewById(R.id.btnLogin);
        cbRememberPass = (CheckBox) findViewById(R.id.cb_remember);

        // get nick and pass if it be remember
        user = ToastVar.get(this, ToastVar.KEY_USER);
        pass = ToastVar.get(this, ToastVar.KEY_PASS);

        if (user != null && pass != null) {
            edtUser.setText(user);
            edtPass.setText(pass);
            cbRememberPass.setChecked(true);
        }

       /* Intent intent = getIntent();
        url = intent.getExtras().getString("url");*/

        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.wait));

        btn_login.setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                try {
                    if(!CheckConnection.getCurrentSsid(this)){
                        bottom_panel.setVisibility(View.VISIBLE);
                    }else {
                        bottom_panel.setVisibility(View.INVISIBLE);
                        login();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_reset:
               reset();
                break;
        }
    }

    private void reset() {
        edtUser.setText("");
        edtPass.setText("");
        edtUser.requestFocus();
    }

    private void login() throws IOException {
        user=edtUser.getText().toString();
        pass=edtPass.getText().toString();
        // not enter nick name
        if (user.length() == 0) {
            edtUser.requestFocus();
            ToastVar.showToast(this, this.getResources().getString(R.string.enter_nick));
            return;
        }
        // not enter pass
        if (pass.length() == 0) {
            edtPass.requestFocus();
            ToastVar.showToast(this, this.getResources().getString(R.string.enter_pass));
            return;
        }
        // save nick and pass
        if (cbRememberPass.isChecked()) {
            ToastVar.save(this, ToastVar.KEY_USER, user);
            ToastVar.save(this, ToastVar.KEY_PASS, pass);
        } else {
            ToastVar.save(this, ToastVar.KEY_USER, null);
            ToastVar.save(this, ToastVar.KEY_PASS, null);
        }
        String user = edtUser.getText().toString();               //+ jsonText();
        String pass = edtPass.getText().toString();

        if(!user.equals("admin")){
            edtUser.requestFocus();
            ToastVar.showToast(this, this.getResources().getString(R.string.user_invalid));
            return;
        }
        progressDialog.show();
        stringRequestActivity.postStringRequest(getApplicationContext(), url,user+jsonText(),pass );
    }

    @Override
    protected void onResume() {
        super.onResume();
        url = "http://" + getIpAddress() + ":8080/";
        Log.e("Check ", CheckConnection.getCurrentSsid(this)+"");
        // set ssid default
        if (!SharePrefMn.getInstance(getApplicationContext()).isChange(ToastVar.KEY_IP)
                ||getIpAddress()!=ToastVar.get(this, ToastVar.KEY_IP)) {
            SharePrefMn.getInstance(getApplicationContext()).change(true, ToastVar.KEY_IP);
            ToastVar.save(this, ToastVar.KEY_IP, getIpAddress());
        }

        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        check=false;
        super.onDestroy();
    }

    @Override
    public void finishLoadJSon(String error, String json) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if(json!=null){
            if(json.contains("login")){
                String []str = json.trim().split("#");
                battery = str[1];
                ToastVar.save(this, ToastVar.KEY_IP, getIpAddress());
                // send json to sever
                ToastVar.showToast(this, this.getResources().getString(R.string.login_success));
                Intent i = new Intent(ActivityLogin.this, ActivityControl.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }else{
                ToastVar.showToast(this, this.getResources().getString(R.string.login_fail));
            }
        }else {
            ToastVar.showToast(this, error);
        }
    }


    private String jsonText() throws IOException {
        String jsonTextDevice = "";
        StringWriter output = new StringWriter();
        DeviceInfo device = JsonDevice.getDevice(getApplicationContext());
        // Chuyển đối tượng Java 'device' thành dữ liệu JSON và ghi vào Writer output.
        JsonDevice.writeJson(output, device);
        jsonTextDevice = output.toString();
        return "#"+jsonTextDevice + "#";
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private String getIpAddress(){
        WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String ipAddress = Formatter.formatIpAddress(ip);
        String split[] = ipAddress.split("\\.");
        String ipAddress_default = split[0]+"."+split[1]+"."+split[2]+".1";
        return ipAddress_default;
    }
}