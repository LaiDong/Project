package com.example.ld.itvbox;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ld on 11/16/16.
 */

public class CustomAppsActivity extends Activity {
    private GridView gv = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_custom_apps);

        gv = (GridView)findViewById(R.id.grid_add_apps);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Map<String, Object> item = (Map<String, Object>)parent.getItemAtPosition(pos);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CustomAppsActivity", "------onResume");
        displayView();
    }

    private List<Map<String, Object>> loadApplications() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        PackageManager manager = getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        final List<ResolveInfo> apps = manager.queryIntentActivities(mainIntent, 0);
        Collections.sort(apps, new ResolveInfo.DisplayNameComparator(manager));

        if (apps != null) {
            final int count = apps.size();

            for (int i = 0; i < count; i++) {
                com.example.ld.itvbox.ApplicationInfo application = new com.example.ld.itvbox.ApplicationInfo();
                ResolveInfo info = apps.get(i);

                application.title = info.loadLabel(manager);
                application.setActivity(new ComponentName(
                                info.activityInfo.applicationInfo.packageName,
                                info.activityInfo.name),
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                application.icon = info.activityInfo.loadIcon(manager);

                map = new HashMap<String, Object>();
                map.put("item_name", application.title.toString());
                map.put("file_path", application.intent);
                map.put("item_type", application.icon);
                map.put("item_symbol", application.componentName);

                list.add(map);
            }

        }

        return list;
    }


    private void displayView() {
        LocalAdapter ad = new LocalAdapter(CustomAppsActivity.this,
                loadApplications(),
                R.layout.add_apps_grid_item,
                new String[] {"item_type", "item_name", "item_sel", "item_bg"},
                new int[] {R.id.item_type, R.id.item_name, R.id.item_sel, R.id.relative_layout});
        gv.setAdapter(ad);
    }
    private void updateView() {
        ((BaseAdapter) gv.getAdapter()).notifyDataSetChanged();
    }
}
