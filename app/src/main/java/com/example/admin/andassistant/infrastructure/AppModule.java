package com.example.admin.andassistant.infrastructure;

import android.app.Application;
import android.content.Context;

import com.example.admin.andassistant.infrastructure.noteapi.NoteApi;
import com.example.admin.andassistant.infrastructure.noteapi.FakeNoteApi;
import com.example.admin.andassistant.usersapi.FakeUserApi;
import com.example.admin.andassistant.usersapi.UserApi;
import com.example.admin.andassistant.widgets.showcase.ShowcasePreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final BaseApplication application;

    public AppModule(Application application) {
        this.application = (BaseApplication) application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public ShowcasePreferences provideShowcasePrefs(Context context) {
        return new ShowcasePreferences(context);
    }

    @Provides
    @Singleton
    public UserApi provideUserApi() {
        return new FakeUserApi();
    }

    @Provides
    @Singleton
    public NoteApi provideNoteApi() {
        return new FakeNoteApi();
    }

}