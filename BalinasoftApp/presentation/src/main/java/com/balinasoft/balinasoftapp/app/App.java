package com.balinasoft.balinasoftapp.app;

import android.app.Application;

import com.balinasoft.balinasoftapp.injection.AppComponent;
import com.balinasoft.balinasoftapp.injection.AppModule;
import com.balinasoft.balinasoftapp.injection.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

/*Fixme очистка токена PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();*/
}
