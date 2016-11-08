package com.example.ld.clienttoserver.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ld on 8/19/16.
 */
public class TextTitle {
    public static void title(String title, Context context){
        TextView titleTextView = new TextView(context);
        titleTextView.setText("");
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setTextSize(16);
        titleTextView.setText(title);
        ((AppCompatActivity)context).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)context).getSupportActionBar().setCustomView(titleTextView);
        ((AppCompatActivity)context).getSupportActionBar().setDisplayShowCustomEnabled(true);
    }
}
