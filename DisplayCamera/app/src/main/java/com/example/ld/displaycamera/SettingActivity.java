package com.example.ld.displaycamera;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by ld on 11/4/16.
 */

public class SettingActivity extends PreferenceActivity {
    ListPreference listimagesizeST,listwhile_balanceST, listexposureST, listeffect_colorST, listisoST,
            listauto_captureST, listcapture_continuityST, listvideo_sizeST, listsavetoST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settingcamera);
        listimagesizeST= (ListPreference) findPreference("imagesizeST");
        listwhile_balanceST= (ListPreference) findPreference("while_balanceST");
        listexposureST= (ListPreference) findPreference("exposureST");
        listeffect_colorST= (ListPreference) findPreference("effect_colorST");
        listisoST= (ListPreference) findPreference("isoST");
        listauto_captureST= (ListPreference) findPreference("auto_captureST");
        listcapture_continuityST= (ListPreference) findPreference("capture_continuityST");
        listvideo_sizeST= (ListPreference) findPreference("video_sizeST");
        listsavetoST= (ListPreference) findPreference("savetoST");

        listimagesizeST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listwhile_balanceST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listexposureST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listeffect_colorST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listisoST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listauto_captureST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listcapture_continuityST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listvideo_sizeST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        listsavetoST.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });
    }
}
