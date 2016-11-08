package com.example.ld.clienttoserver.activity.usermanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;
import com.example.ld.clienttoserver.activity.control.ActivityControl;
import com.example.ld.clienttoserver.request.CheckConnection;
import com.example.ld.clienttoserver.request.volleylibs.StringRequestActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ld on 8/13/16.
 */
public class User extends AppCompatActivity {
    ListView list;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        list = (ListView) findViewById(R.id.list_user);

        Intent intent = getIntent();
        String listIpaddress = intent.getExtras().getString("user_management");
        String Ipaddress = listIpaddress.replace("user[", "");
        Ipaddress.replaceAll("\\[", "");
        Ipaddress.replaceAll("]","");
        List<String> list = new ArrayList<>(Arrays.asList(Ipaddress.split(",")));
        Log.e("list: ", list.toString());

        adapter=new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);

        TextView title = new TextView(this);
        title.setText("");
        title.setTextColor(Color.WHITE);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(16);
        title.setText("User management");
        ((AppCompatActivity)this).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)this).getSupportActionBar().setCustomView(title);
        ((AppCompatActivity)this).getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.setAdapter(adapter);
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
