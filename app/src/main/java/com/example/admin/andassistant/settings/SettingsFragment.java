package com.example.admin.andassistant.settings;

import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.infrastructure.BaseApplication;
import com.example.admin.andassistant.widgets.showcase.ShowcasePreferences;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Inject
    ShowcasePreferences showcasePreferences;

    boolean isShowcaseEnabled = false;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        inject();
        setFileName();
        initView(s);

        setPropertyListeners();
    }

    private void setPropertyListeners() {
        showcasePropertyListener();
    }

    private void inject() {
        ((BaseApplication) getContext().getApplicationContext()).getComponent().inject(this);
    }

    private void setFileName() {
        getPreferenceManager().setSharedPreferencesName(showcasePreferences.getFileName());
    }

    private void initView(String s) {
        setPreferencesFromResource(R.xml.app_preferences, s);
    }

    private void showcasePropertyListener() {
        CheckBoxPreference showcasePref = (CheckBoxPreference) findPreference(getString(R.string.pref_showcase_setting));
        showcasePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                isShowcaseEnabled = (boolean) o;
                return true;
            }
        });

        isShowcaseEnabled = showcasePref.isChecked();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (!isShowcaseEnabled) {
            showcasePreferences.reset();
        }
    }

}
