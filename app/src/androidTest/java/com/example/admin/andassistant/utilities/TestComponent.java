package com.example.admin.andassistant.utilities;

import com.example.admin.andassistant.infrastructure.AppComponent;
import com.example.admin.andassistant.infrastructure.noteapi.NoteApi;
import com.example.admin.andassistant.usersapi.UserApi;
import com.example.admin.andassistant.widgets.showcase.ShowcasePreferences;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component
public interface TestComponent extends AppComponent {

    @Component.Builder
    interface Builder {
        TestComponent build();

        @BindsInstance
        Builder userApi(UserApi userApi);

        @BindsInstance
        Builder showcasePreferences(ShowcasePreferences showcasePrefs);

        @BindsInstance
        Builder noteApi(NoteApi noteApi);
    }

}
