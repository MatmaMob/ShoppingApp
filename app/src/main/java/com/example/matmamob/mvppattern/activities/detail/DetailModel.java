package com.example.matmamob.mvppattern.activities.detail;

import com.example.matmamob.mvppattern.db.RealmHelper;
import com.example.matmamob.mvppattern.db.data.ListIngredients;

import io.realm.Realm;
import io.realm.RealmList;

public class DetailModel implements DetailMVP.Model {
    private RealmHelper databaseHelper;

    @Override
    public void saveIngredient(String ingredient, RealmList<String> ingredientsList) {
        databaseHelper.saveIngedient(ingredient, ingredientsList);
    }

    @Override
    public void saveListData(int id, RealmList<String> listIngredients) {
        databaseHelper.saveIngredientList(id, listIngredients);
    }

    @Override
    public RealmList<String> loadData(int id) {
        return databaseHelper.getIngredient(id);
    }

    @Override
    public void setRealmHelper(Realm realm) {
        databaseHelper = new RealmHelper(realm);
    }

    @Override
    public void removeElement(RealmList<String> realmList, int position) {
        databaseHelper.removeRealmListElement(realmList, position);
    }
}
