package com.example.ld.displaycamera;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    ImageView btn_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_video, container, false);
        btn_setting = (ImageView) view.findViewById(R.id.btn_setting_video);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);
            }
        });
        return view;
    }


}
