package com.example.coderspeak.mvppattern.fragments.Archieved;

import com.example.coderspeak.mvppattern.db.RealmHelper;
import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ArchievedModel implements ArchievedMVP.Model {
    private RealmHelper dbHelper;

    @Override
    public List<ShoppingList> loadShoppingListData() {
        return dbHelper.getAllArchievedShoppingLists();
    }

    @Override
    public void setArchievedElement(boolean isArchieved, int id) {
        dbHelper.setArchievedElement(isArchieved, id);
    }

    @Override
    public void close(Realm realm) {
        realm.close();
    }

    @Override
    public void setRealmHelper(Realm realm) {
        dbHelper = new RealmHelper(realm);
    }
}
