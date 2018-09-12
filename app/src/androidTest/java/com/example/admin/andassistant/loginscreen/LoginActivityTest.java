package com.example.admin.andassistant.loginscreen;

import android.view.View;
import com.example.admin.andassistant.utilities.ActivityTest;
import com.example.admin.andassistant.R;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.assertj.android.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginActivityTest extends ActivityTest<LoginActivity> {

    private void pressRegisterButton() {
        onView(withId(R.id.loginButtonRegister)).perform(click());
    }

    private void pressLoginButton() {
        onView(withId(R.id.loginButtonLogin)).perform(click());
    }

    private void typeInCredentials(String email, String password) {
        onView(withId(R.id.loginEmail)).perform(typeText(email));
        onView(withId(R.id.loginPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.loginRepeatedPassword)).perform(typeText(password), closeSoftKeyboard());
    }

    @Test
    public void givenLoginMode_titleIsLogin() {
        assertTitle(activity.getString(R.string.login_activity_title), activity);
    }

    @Test
    public void switchToRegisterMode() {
        pressRegisterButton();

        View repeatPassword = activity.findViewById(R.id.loginRepeatedPassword);
        assertThat(repeatPassword).isVisible();
        assertTitle(activity.getString(R.string.login_activity_register_title), activity);
    }

    @Test
    public void givenRegisterMode_switchToLoginMode() {
        pressRegisterButton();
        pressLoginButton();

        View repeatPassword = activity.findViewById(R.id.loginRepeatedPassword);
        assertThat(repeatPassword).isNotVisible();
        assertTitle(activity.getString(R.string.login_activity_title), activity);
    }

    @Test
    public void registerUser_thenLogin() throws Exception {
        String email = "a@a.com";
        String password = "123";

        pressRegisterButton();
        typeInCredentials(email, password);
        pressRegisterButton();
        pressLoginButton();

        assertTrue(mockUserApi.isRegistered(email));
        assertTrue(mockUserApi.isLogged(email));

//        Thread.sleep(700);
        assertTitle(activity.getString(R.string.title_home));
    }

}