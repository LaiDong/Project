package com.example.ld.displaycamera;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ld on 11/2/16.
 */

public class Activity_Setting extends AppCompatActivity {
   /* @InjectView(R.id.size)
    RelativeLayout rlsize_image;
    @InjectView(R.id.while_balance)
    RelativeLayout rlbalance;
    @InjectView(R.id.exposure_value)
    RelativeLayout rlexposure;
    @InjectView(R.id.effect_color)
    RelativeLayout rleffect_color;
    @InjectView(R.id.iso)
    RelativeLayout rliso;
    @InjectView(R.id.capture_auto)
    RelativeLayout rlcapture_auto;
    @InjectView(R.id.capture_continuity)
    RelativeLayout rlcapture_continuity;
    @InjectView(R.id.video_size)
    RelativeLayout rlvideo_size;
    @InjectView(R.id.saveto)
    RelativeLayout rlsaveto;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.size)
    void setSize(){
        CharSequence[] values = getResources().getStringArray(R.array.image_size);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.image_size), values);
    }

    @OnClick(R.id.exposure_value)
    void setExposure(){
        CharSequence[] values = getResources().getStringArray(R.array.exposure_value);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.exposure_value),values);
    }

    @OnClick(R.id.while_balance)
    void setWhilebalance(){
        CharSequence[] values = getResources().getStringArray(R.array.while_balance);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.while_balance),values);
    }

    @OnClick(R.id.effect_color)
    void setEffectcolor(){
        CharSequence[] values = getResources().getStringArray(R.array.effect_color);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.effect_color),values);
    }

    @OnClick(R.id.iso)
    void setISO(){
        CharSequence[] values = getResources().getStringArray(R.array.iso);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.iso),values);
    }

    @OnClick(R.id.capture_auto)
    void captureAuto(){
        CharSequence[] values = getResources().getStringArray(R.array.auto_capture);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.capture_auto), values);
    }

    @OnClick(R.id.capture_continuity)
    void setCapturecontinuity(){
        CharSequence[] values = getResources().getStringArray(R.array.capture_continuity);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.capture_continuity),values);
    }

    @OnClick(R.id.video_size)
    void setVideosize(){
        CharSequence[] values = getResources().getStringArray(R.array.video_size);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.video_size),values);
    }

    @OnClick(R.id.saveto)
    void setSaveto(){
        CharSequence[] values = getResources().getStringArray(R.array.saveto);
        ItemsDialog.getInstance(this).CreateAlertDialogWithRadioButtonGroup(getString(R.string.saveto),values);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
