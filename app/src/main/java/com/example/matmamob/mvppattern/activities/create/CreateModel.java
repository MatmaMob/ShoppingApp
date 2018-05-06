package com.example.matmamob.mvppattern.activities.create;

import android.content.SharedPreferences;

import com.example.matmamob.mvppattern.db.RealmHelper;
import com.example.matmamob.mvppattern.db.data.ShoppingList;

import java.util.Date;

import io.realm.Realm;

public class CreateModel implements CreateMVP.Model {
    private RealmHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private long time;
    private int id;

    @Override
    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveListData(String title) {
        Date date = new Date();
        time = date.getTime() / 1000;
        databaseHelper.saveShoppingList(sharedPreferences, title, time);
    }

    @Override
    public void saveId() {
        id = sharedPreferences.getInt("id", 0);
        id++;
        sharedPreferences.edit().putInt("id", id).apply();
    }

    @Override
    public void setRealmHelper(Realm realm) {
        databaseHelper = new RealmHelper(realm);
    }
}
