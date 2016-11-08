package com.example.ld.clienttoserver.activity.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.activity.menusetting.ActivitySetting;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LD on 7/23/2016.
 */
public class ActivityChangePass extends AppCompatActivity implements View.OnClickListener, StringRequestActivity.OnRequestStringListener{
    private EditText editPass, editNewPass, editRePass;
    private ProgressDialog progressDialog;
    private StringRequestActivity stringRequestActivity;
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        ButterKnife.inject(this);
        editPass = (EditText) findViewById(R.id.edit_pass);
        editNewPass = (EditText) findViewById(R.id.edit_new_pass);
        editRePass = (EditText) findViewById(R.id.edit_re_pass);
        findViewById(R.id.btn_change).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
        editPass.setText(ToastVar.get(this, ToastVar.KEY_PASS));

        stringRequestActivity = new StringRequestActivity();
        stringRequestActivity.setOnRequestStringListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.wait));

        TextTitle.title("Modified Password", this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reset:
                reset();
                break;
            case R.id.btn_change:
                if(!CheckConnection.getCurrentSsid(this)){
                    bottom_panel.setVisibility(View.VISIBLE);
                }else {
                    bottom_panel.setVisibility(View.INVISIBLE);
                    change_pass();
                }
                break;
        }
    }

    private void reset() {
        editNewPass.setText("");
        editRePass.setText("");
        editNewPass.requestFocus();
    }

    private void change_pass() {
        String pass = editPass.getText().toString().trim();
        String repass = editRePass.getText().toString().trim();
        String newpass = editNewPass.getText().toString().trim();

        // not enter nick current pass
        if (pass.length() == 0) {
            editPass.requestFocus();
            ToastVar.showToast(this, getResources().getString(R.string.enter_currentpass));
            return;
        }

        // not enter new pass
        if (newpass.length() == 0) {
            editNewPass.requestFocus();
            ToastVar.showToast(this, getResources().getString(R.string.enter_new_pass));
            return;
        }

        // not enter repass
        if (repass.length() == 0) {
            editRePass.requestFocus();
            ToastVar.showToast(this, getResources().getString(R.string.enter_re_pass));
            return;
        }

        // repass not equal newpass
        if (!repass.equals(newpass)) {
            editRePass.requestFocus();
            ToastVar.showToast(this, getResources().getString(R.string.pass_not_match));
            return;
        }
        stringRequestActivity.postStringRequest(this, ActivityLogin.url, ToastVar.CHANGEPASS, repass);
        progressDialog.show();
    }

    @Override
    public void finishLoadJSon(String error, String json) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if(json!=null){
            if(json.equals(ToastVar.SUCCESS)){
                ToastVar.showToast(this, getResources().getString(R.string.change_success));
                Intent intent = new Intent(this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }else{
                ToastVar.showToast(this, getResources().getString(R.string.change_fail));
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
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityAccount.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

}
