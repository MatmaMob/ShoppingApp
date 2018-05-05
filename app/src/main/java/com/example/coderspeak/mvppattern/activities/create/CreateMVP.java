package com.example.coderspeak.mvppattern.activities.create;

import android.content.SharedPreferences;

import io.realm.Realm;

public interface CreateMVP {
    interface Model {
        void setSharedPreferences(SharedPreferences sharedPreferences);

        void saveListData(String title);

        void saveId();

        void close(Realm realm);

        void setRealmHelper(Realm realm);
    }

    interface View {
    }

    interface Presenter {
        void setView(CreateMVP.View view);

        void provideData(String title);

        void provideSharedPreferences(SharedPreferences sharedPreferences);

        void closeDatabase(Realm realm);

        void setRealm(Realm realm);
    }
}
