package com.example.ld.displaycamera;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FilterFragment extends Fragment {
    ImageView btn_setting;

    public FilterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_camera_filter, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
