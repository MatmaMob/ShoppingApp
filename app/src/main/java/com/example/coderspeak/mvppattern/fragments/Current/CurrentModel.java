package com.example.coderspeak.mvppattern.fragments.Current;

import com.example.coderspeak.mvppattern.db.RealmHelper;
import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CurrentModel implements CurrentMVP.Model {
    private RealmHelper databaseHelper;

    @Override
    public List<ShoppingList> loadCurrentShoppingLists() {
        return databaseHelper.getAllCurrentShoppingLists();
    }

    @Override
    public void setArchievedElement(boolean isArchieved, int id) {
        databaseHelper.setArchievedElement(isArchieved, id);
    }

    @Override
    public void setRealmHelper(Realm realm) {
        databaseHelper = new RealmHelper(realm);
    }

    @Override
    public void close(Realm realm) {
        realm.close();
    }
}
