package com.example.ld.clienttoserver.activity.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.TextTitle;
import com.example.ld.clienttoserver.activity.login.ActivityLogin;
import com.example.ld.clienttoserver.activity.main.ToastVar;
import com.example.ld.clienttoserver.activity.menusetting.ActivitySetting;
import com.example.ld.clienttoserver.request.CheckConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ld on 8/19/16.
 */
public class ActivityAccount extends AppCompatActivity {
    @InjectView(R.id.bottom_panel)
    LinearLayout bottom_panel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanager_layout);
        ButterKnife.inject(this);

        TextTitle.title("Setting Account", this);

    }

    @OnClick(R.id.id_account_modified_password)
    void modified_password(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, ActivityChangePass.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.id_acount_logout)
    void logout(){
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAccount.this);
            builder.setTitle("Reboot device")
                    .setMessage("Are you sure want to logout account?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(ActivityAccount.this, ActivityLogin.class);
                            startActivity(intent);
                            finish();
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
    protected void onResume() {
        super.onResume();
        if(!CheckConnection.getCurrentSsid(this)){
            bottom_panel.setVisibility(View.VISIBLE);
        }else {
            bottom_panel.setVisibility(View.INVISIBLE);
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
}
