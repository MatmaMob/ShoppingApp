package com.example.coderspeak.mvppattern.fragments.Archieved;

import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.List;

import io.realm.Realm;

public class ArchievedPresenter implements ArchievedMVP.Presenter {
    private ArchievedMVP.Model model;
    private ArchievedMVP.View view;

    public ArchievedPresenter(ArchievedMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ArchievedMVP.View view) {
        this.view = view;
    }

    @Override
    public List<ShoppingList> provideData(Realm realm) {
        return model.loadShoppingListData();
    }

    @Override
    public void setRealm(Realm realm) {
        model.setRealmHelper(realm);
    }

    @Override
    public void setAdapterData(boolean isArchieved, int id) {
        model.setArchievedElement(isArchieved, id);
    }

    @Override
    public void closeDatabase(Realm realm) {
        model.close(realm);
    }
}
