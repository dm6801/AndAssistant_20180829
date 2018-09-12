package com.example.admin.andassistant.infrastructure;

import com.example.admin.andassistant.editnote.EditNoteFragment;
import com.example.admin.andassistant.loginscreen.LoginActivity;
import com.example.admin.andassistant.noteslist.NotesFragment;
import com.example.admin.andassistant.settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(LoginActivity activity);

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(NotesFragment fragment);

    void inject(EditNoteFragment fragment);
}