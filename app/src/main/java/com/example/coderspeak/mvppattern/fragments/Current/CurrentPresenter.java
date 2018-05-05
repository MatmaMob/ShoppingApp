package com.example.coderspeak.mvppattern.fragments.Current;

import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.List;

import io.realm.Realm;

public class CurrentPresenter implements CurrentMVP.Presenter {
    private CurrentMVP.Model model;
    private CurrentMVP.View view;

    public CurrentPresenter(CurrentMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CurrentMVP.View view) {
        this.view = view;
    }

    @Override
    public void setRealm(Realm realm) {
        model.setRealmHelper(realm);
    }

    @Override
    public List<ShoppingList> provideData() {
        return model.loadCurrentShoppingLists();
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
