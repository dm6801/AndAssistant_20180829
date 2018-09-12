package com.example.admin.andassistant.utilities;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.admin.andassistant.R;

import java.util.Objects;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

public abstract class TestUtilities {

    public static void assertTitle(String title, AppCompatActivity activity) {
        assertEquals(title, Objects.requireNonNull(activity.getSupportActionBar()).getTitle());
    }

    public static void assertTitle(String title) {
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText(title)));
    }

    public static void navigateUp() {
        onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
    }

}
