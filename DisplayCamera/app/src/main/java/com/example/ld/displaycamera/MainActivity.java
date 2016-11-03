package com.example.ld.displaycamera;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @InjectView(R.id.btn_setting)
    ImageView btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_setting)
    void btn_setting(){
        Intent i = new Intent(this, Activity_Setting.class);
        startActivity(i);
    }
}
