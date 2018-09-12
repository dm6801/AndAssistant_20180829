package com.example.admin.andassistant.home;

import com.example.admin.andassistant.R;

import org.junit.Test;

public class NavigationTest extends HomeActivityTest {

    @Test
    public void navigationIsWorking() {
        dashboard();
        settings();
        pressUpButton_to_home();
    }

    private void dashboard() {
        super.navigateToDashboard();
        assertTitle(activity.getString(R.string.title_dashboard));
    }

    private void settings() {
        super.navigateToSettings();
        assertTitle(activity.getString(R.string.title_settings));
    }

    private void pressUpButton_to_home() {
        super.navigateUp();
        assertTitle(activity.getString(R.string.title_home));
    }

}
