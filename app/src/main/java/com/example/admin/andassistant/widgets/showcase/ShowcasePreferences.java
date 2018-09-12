package com.example.admin.andassistant.widgets.showcase;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.admin.andassistant.R;

import java.util.Map;

public class ShowcasePreferences {
    private String pref_name;
    private String watchedSuffix;
    private String fileName;

    private SharedPreferences sharedPrefs;

    public ShowcasePreferences(Context context) {
        String fileNameSuffix = context.getString(R.string.pref_filename_suffix);
        fileName = context.getPackageName() + fileNameSuffix;
        sharedPrefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        pref_name = context.getString(R.string.pref_showcase_setting);
        watchedSuffix = context.getString(R.string.pref_showcase_suffix);

        init();
    }

    public boolean isEnabled() {
        return sharedPrefs.getBoolean(pref_name, false);
    }

    public void reset() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Map<String, ?> prefsMap = sharedPrefs.getAll();
        for (String key : prefsMap.keySet()) {
            if (key.toLowerCase().endsWith(watchedSuffix)) {
                editor.remove(key);
            }
        }

        editor.apply();
    }

    boolean isApplicable(String showcaseName) {
        return !sharedPrefs.contains(showcaseName.toLowerCase() + watchedSuffix);
    }

    void markAsWatched(String showcaseName) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(showcaseName.toLowerCase() + watchedSuffix, true);
        editor.apply();
    }

    private void init() {
        if (isFirstUse()) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(pref_name, true);
            editor.apply();
        }
    }

    private boolean isFirstUse() {
        return !sharedPrefs.contains(pref_name);
    }

    public String getFileName() {
        return fileName;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPrefs;
    }

}
