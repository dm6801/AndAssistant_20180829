package com.example.admin.andassistant.utilities;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.andassistant.infrastructure.noteapi.FakeNoteApi;
import com.example.admin.andassistant.infrastructure.noteapi.NoteApi;
import com.example.admin.andassistant.usersapi.FakeUserApi;
import com.example.admin.andassistant.usersapi.UserApi;
import com.example.admin.andassistant.widgets.showcase.ShowcasePreferences;

import org.junit.Before;
import org.junit.Rule;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class ActivityTest<T extends AppCompatActivity> {

    protected T activity;

    protected UserApi mockUserApi;
    protected NoteApi mockNoteApi;
    protected ShowcasePreferences mockShowcasePrefs;

    @Rule
    public ActivityTestRule<T> testRule = new ActivityTestRule<>(getGenericClassType(), true, false);

    private Class<T> getGenericClassType() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType)) {
            type = ((Class<?>) type).getGenericSuperclass();
        }

        return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    protected void setupMocks() {
        TestApplication application = (TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();

        mockUserApi = new FakeUserApi();
        mockNoteApi = new FakeNoteApi();

        mockShowcasePrefs = spy(new ShowcasePreferences(application));
        when(mockShowcasePrefs.isEnabled()).thenReturn(false);

        application.setComponent(
                DaggerTestComponent.builder()
                        .showcasePreferences(mockShowcasePrefs)
                        .userApi(mockUserApi)
                        .noteApi(mockNoteApi)
                        .build()
        );
    }

    @Before
    public void launchActivity() {
        setupMocks();
        activity = testRule.launchActivity(null);
    }

    protected void assertTitle(String title, AppCompatActivity activity) {
        TestUtilities.assertTitle(title, activity);
    }

    protected void assertTitle(String title) {
        TestUtilities.assertTitle(title);
    }

    protected void navigateUp() {
        TestUtilities.navigateUp();
    }

}
