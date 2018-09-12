package com.example.admin.andassistant.dashboard;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.home.HomeActivityTest;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class DashboardFragmentTest extends HomeActivityTest {

//    private DashboardFragment fragment;

    @Before
    @Override
    public void launchActivity() {
        super.launchActivity();

        navigateToDashboard();
//        fragment = getHostedFragment();
    }

    @Test
    public void navigateToNotes() {
        onView(withId(R.id.navNotes)).perform(click());
        assertTitle(activity.getString(R.string.title_notes));
    }

}