package com.example.admin.andassistant.infrastructure;

import android.app.Application;

public class BaseApplication extends Application {

    private AppComponent appComponent = createComponent();

    public void setComponent(AppComponent component) {
        appComponent = component;
    }

    public AppComponent getComponent() {
        return appComponent;
    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
