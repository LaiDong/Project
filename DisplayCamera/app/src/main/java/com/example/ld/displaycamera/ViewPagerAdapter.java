package com.example.ld.displaycamera;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by LD on 11/7/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    public static int totalPage = 4;
    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch (position) {
            case 0:
                f = new CameraFragment();
                break;
            case 1:
                f = new VideoFragment();
                break;
            case 2:
                f = new FilterFragment();
                break;
            case 3:
                f = new ImageFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return totalPage;
    }
}
