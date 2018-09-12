package com.example.admin.andassistant.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.v4.app.Fragment;

import com.example.admin.andassistant.utilities.ActivityTest;
import com.example.admin.andassistant.R;

import org.junit.AfterClass;

import java.util.Objects;

import androidx.navigation.fragment.NavHostFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.spy;

public class HomeActivityTest extends ActivityTest<HomeActivity> {

    protected void navigateToHome() {
        onView(withId(R.id.navHome)).perform(click());
    }

    protected void navigateToDashboard() {
        onView(withId(R.id.navDashboard)).perform(click());
    }

    protected void navigateToSettings() {
        onView(withId(R.id.navSettings)).perform(click());
    }

    @SuppressWarnings("unchecked cast")
    protected <T extends Fragment> T getHostedFragment() {
        NavHostFragment navHost = (NavHostFragment) Objects.requireNonNull(activity.getSupportFragmentManager().getPrimaryNavigationFragment());
        return (T) navHost.getChildFragmentManager().getPrimaryNavigationFragment();
    }

    @AfterClass
    public static void tearDown() {
        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences sharedPrefs = context.getSharedPreferences(context.getPackageName() + context.getString(R.string.pref_filename_suffix), Context.MODE_PRIVATE);

        sharedPrefs.edit().clear().commit();
    }

}