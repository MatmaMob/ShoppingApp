package com.example.matmamob.mvppattern.activities.detail;

import io.realm.Realm;
import io.realm.RealmList;

public class DetailPresenter implements DetailMVP.Presenter {
    private DetailMVP.View view;
    private DetailMVP.Model model;

    public DetailPresenter(DetailMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(DetailMVP.View view) {
        this.view = view;
    }

    @Override
    public RealmList<String> provideData(int id) {
        return model.loadData(id);
    }

    @Override
    public void provideSaveList(int id, RealmList<String> listIngredients) {
        model.saveListData(id, listIngredients);
    }

    @Override
    public void provideSaveIngredient(String ingredient, RealmList<String> listIngredients) {
        model.saveIngredient(ingredient, listIngredients);
    }

    @Override
    public void setRealm(Realm realm) {
        model.setRealmHelper(realm);
    }

    @Override
    public void provideRemoveElement(RealmList<String> realmList, int position) {
        model.removeElement(realmList, position);
    }
}
