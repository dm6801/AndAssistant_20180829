package com.example.admin.andassistant.settings;

import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.home.HomeActivityTest;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.core.api.Assertions.assertThat;

public class SettingsFragmentTest extends HomeActivityTest {
    public static final String TAG = "SettingsFragmentTest";

    private SettingsFragment fragment;

    @Before
    @Override
    public void launchActivity() {
        super.launchActivity();

        navigateToSettings();
        fragment = getHostedFragment();
    }

    @Test
    public void miscellaneousHeader_withShowcase_checkboxProperty() {
        Preference showcasePref = fragment.findPreference(activity.getString(R.string.pref_showcase_setting));
        String prefSummary = showcasePref.getSummary().toString();

        onView(withText(activity.getString(R.string.settings_misc_label))).check(matches(isDisplayed()));
        onView(withText(prefSummary)).check(matches(isDisplayed()));
        assertThat(showcasePref).isInstanceOf(CheckBoxPreference.class);
    }

}