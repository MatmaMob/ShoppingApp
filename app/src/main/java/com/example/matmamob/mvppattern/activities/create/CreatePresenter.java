package com.example.matmamob.mvppattern.activities.create;

import android.content.SharedPreferences;

import io.realm.Realm;

public class CreatePresenter implements CreateMVP.Presenter {
    CreateMVP.View view;
    CreateMVP.Model model;

    public CreatePresenter(CreateMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CreateMVP.View view) {
        this.view = view;
    }

    @Override
    public void provideData(String title) {
        model.saveListData(title);
        model.saveId();
    }

    @Override
    public void provideSharedPreferences(SharedPreferences sharedPreferences) {
        model.setSharedPreferences(sharedPreferences);
    }

    @Override
    public void setRealm(Realm realm) {
        model.setRealmHelper(realm);
    }
}
