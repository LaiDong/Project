package com.example.ld.itvbox;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @InjectView(R.id.playStoreIv)
    ImageView img_playStore;
    @InjectView(R.id.fullAppIv)
    ImageView img_fullApp;
    @InjectView(R.id.youtubeIv)
    ImageView img_youtube;
    @InjectView(R.id.browserIv)
    ImageView img_browser;
    @InjectView(R.id.settingIv)
    ImageView img_setting;
    @InjectView(R.id.fileManagerIv)
    ImageView img_fileManager;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private PackageManager manager;
    public static List<ApplicationInfo> listAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_number_one_layout);
        ButterKnife.inject(this);
     /*   manager = getPackageManager();
        listAll = manager.getInstalledApplications(PackageManager.GET_META_DATA);
        final List<String> allApp = new ArrayList<>();
        for(ApplicationInfo app : listAll){
            try {
                String nameApp = (String) manager.getApplicationLabel(manager
                        .getApplicationInfo(app.processName,
                                PackageManager.GET_META_DATA));
                allApp.add(nameApp);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }*/


    }

    @OnClick(R.id.playStoreIv)
    void clickPlayStore(){
       /* final GridView gridViewApp = new GridView(this);
        gridViewApp.setAdapter(new GridViewAdapter(this, R.layout.grid_item_layout, manager, MainActivity.listAll));
        gridViewApp.setNumColumns(5);
        gridViewApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _parent, View _v, int _position, long _id) {
                // do something here

            }
        });

        // Set grid view to alertDialog
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(gridViewApp);
        builder.setTitle("All Application");
        builder.show().getWindow().setLayout(1500, 1000);*/

        Intent intent = new Intent(this, CustomAppsActivity.class);
        startActivity(intent);
    }



}
