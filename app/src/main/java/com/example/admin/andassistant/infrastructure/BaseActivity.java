package com.example.admin.andassistant.infrastructure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.andassistant.usersapi.UserApi;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected UserApi userApi;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @SuppressWarnings("ConstantConditions")
    private void inject() {
        ((BaseApplication) getApplicationContext()).getComponent().inject(this);
    }

}
