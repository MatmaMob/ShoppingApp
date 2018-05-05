package com.example.coderspeak.mvppattern.dagger;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DaggApp extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
        initRealmConfiguration();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
