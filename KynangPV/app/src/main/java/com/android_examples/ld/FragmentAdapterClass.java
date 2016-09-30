package com.android_examples.ld;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android_examples.ld.kinhnghiem.KinhNghiem_Activity;
import com.android_examples.ld.tracnghiem.TracNghiem_Activity;

/**
 * Created by JUNED on 5/30/2016.
 */
public class FragmentAdapterClass extends FragmentStatePagerAdapter {

    int TabCount;

    public FragmentAdapterClass(FragmentManager fragmentManager, int CountTabs) {

        super(fragmentManager);

        this.TabCount = CountTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TracNghiem_Activity tab1 = new TracNghiem_Activity();
                return tab1;

            case 1:
                KinhNghiem_Activity tab2 = new KinhNghiem_Activity();
                return tab2;

            case 2:
                MauCV_Activity tab3 = new MauCV_Activity();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}